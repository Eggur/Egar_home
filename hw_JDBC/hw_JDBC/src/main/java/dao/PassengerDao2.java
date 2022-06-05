package dao;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PassengerDao2 implements PassengerDao {

    private static PassengerDao2 instance;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/train_station";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM passenger";
    private static final String SELECT_TICKETS_QUERY = "SELECT * FROM ticket t " +
            "JOIN ticket_passenger tp ON tp.ticket_id = t.id " +
            "WHERE tp.passenger_id = ?";
    private static final String SELECT_TRIP_QUERY = "SELECT * FROM trip ti " +
            "JOIN ticket t ON t.trip_id = ti.id " +
            "WHERE t.id = ?";
    private static final String SELECT_TRAIN_QUERY = "SELECT * FROM train tr " +
            "JOIN trip ti ON ti.train_id = tr.id " +
            "WHERE ti.id = ?";
    private static final String SELECT_TRAINLINE_QUERY = "SELECT * FROM trainline tl " +
            "JOIN train tr ON tr.trainline_id = tl.id " +
            "WHERE tr.id = ?";
    private static final String SELECT_PASSENGER_BY_ID = "SELECT * FROM passenger WHERE id = ?";
    private static final String SAVE_PASSENGER_QUERY = "INSERT INTO passenger(first_name, last_name, phone) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_PASSENGER_QUERY = "UPDATE passenger " +
            "SET first_name = ?, last_name = ?, phone = ? WHERE id = ?";
    private static final String DELETE_PASSENGER_QUERY = "DELETE FROM passenger WHERE id = ?";

    private PassengerDao2() {
    }

    public static PassengerDao2 getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PassengerDao2();
        }
        return instance;
    }

    @Override
    public List<PassengerEntity> findAll() {
        return query(connection -> {
            try (connection) {
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY);

                List<PassengerEntity> passengerEntities = new ArrayList<>();
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    PassengerEntity passenger = getNewPassengerFromResultSet(connection, rs);
                    passengerEntities.add(passenger);
                }
                return passengerEntities;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Optional<PassengerEntity> findById(Integer id) {
        return query(connection -> {
            try (connection) {
                PreparedStatement ps = connection.prepareStatement(SELECT_PASSENGER_BY_ID);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                PassengerEntity passenger = null;
                if (rs.next()) {
                    passenger = getNewPassengerFromResultSet(connection, rs);
                }
                return Optional.ofNullable(passenger);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void save(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(SAVE_PASSENGER_QUERY);

                ps.setString(1, passenger.getFirstName());
                ps.setString(2, passenger.getLastName());
                ps.setString(3, passenger.getPhone());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public void update(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(UPDATE_PASSENGER_QUERY);

                ps.setString(1, passenger.getFirstName());
                ps.setString(2, passenger.getLastName());
                ps.setString(3, passenger.getPhone());
                ps.setInt(4, passenger.getId());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public void delete(PassengerEntity passenger) {
        query(connection -> {
            try (connection) {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(DELETE_PASSENGER_QUERY);

                ps.setInt(1, passenger.getId());
                ps.execute();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    private <T> T query(Function<Connection, T> s) {
        try (Connection conn = DriverManager.getConnection(DB_URL, "postgres", "123")) {
            return s.apply(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PassengerEntity getNewPassengerFromResultSet(Connection connection, ResultSet rs) throws SQLException {
        PassengerEntity passenger = new PassengerEntity();

        passenger.setId(rs.getInt("id"));
        passenger.setFirstName(rs.getString("first_name"));
        passenger.setLastName(rs.getString("last_name"));
        passenger.setPhone(rs.getString("phone"));
        passenger.setTicketEntityList(selectTickets(connection, passenger));

        for (TicketEntity ticket : passenger.getTicketEntityList()) {
            ticket.setTripEntity(selectTrip(connection, ticket));
        }
        return passenger;
    }

    private List<TicketEntity> selectTickets(Connection connection, PassengerEntity passenger) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TICKETS_QUERY);

        ps.setInt(1, passenger.getId());

        List<TicketEntity> ticketEntities = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TicketEntity ticket = new TicketEntity();
            ticket.setId(rs.getInt("id"));
            ticket.setFare(rs.getDouble("fare"));
            ticket.setCurrency(rs.getString("currency"));
            ticket.setExpired(rs.getBoolean("expired"));

            ticketEntities.add(ticket);
        }
        return ticketEntities;
    }

    private TripEntity selectTrip(Connection connection, TicketEntity ticket) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TRIP_QUERY);

        ps.setInt(1, ticket.getId());

        TripEntity trip = new TripEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            trip.setId(rs.getInt("id"));
            trip.setFrom(rs.getString("from"));
            trip.setTo(rs.getString("to"));
            trip.setDepartureTime(rs.getTime("departure_time"));
            trip.setArrivalTime(rs.getTime("arrival_time"));
            trip.setTrainEntity(selectTrain(connection, trip));
        }
        return trip;
    }

    private TrainEntity selectTrain(Connection connection, TripEntity flight) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TRAIN_QUERY);

        ps.setInt(1, flight.getId());

        TrainEntity train = new TrainEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            train.setId(rs.getInt("id"));
            train.setModel(rs.getString("model"));
            train.setLicensePlate(rs.getString("license_plate"));
            train.setTrainlineEntity(selectTrainline(connection, train));
        }
        return train;
    }

    private TrainlineEntity selectTrainline(Connection connection, TrainEntity train) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TRAINLINE_QUERY);

        ps.setInt(1, train.getId());

        TrainlineEntity trainline = new TrainlineEntity();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            trainline.setId(rs.getInt("id"));
            trainline.setName(rs.getString("name"));
            trainline.setPhone(rs.getString("phone"));
        }
        return trainline;
    }

}


