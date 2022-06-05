package entity;

import lombok.Data;

import java.util.Date;

@Data
public class TripEntity {

    private Integer id;
    private String from;
    private String to;
    private Date departureTime;
    private Date arrivalTime;
    private TrainEntity trainEntity;

}
