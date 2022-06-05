DROP TABLE IF EXISTS trainline CASCADE;
DROP TABLE IF EXISTS train CASCADE;
DROP TABLE IF EXISTS trip CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS ticket_passenger CASCADE;

CREATE TABLE trainline
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 1024)
        CONSTRAINT PK_trainline PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    phone VARCHAR(64) NOT NULL
);

CREATE TABLE train
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 1024)
        CONSTRAINT PK_train PRIMARY KEY,
    model VARCHAR(64) NOT NULL,
    license_plate VARCHAR(30) NOT NULL,
    trainline_id INTEGER NOT NULL
        CONSTRAINT FK_trainline REFERENCES trainline (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE trip
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 1024)
        CONSTRAINT PK_flight PRIMARY KEY,
    "from" VARCHAR(64) NOT NULL,
    "to" VARCHAR(64) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    train_id INTEGER
        CONSTRAINT FK_train REFERENCES train (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE passenger
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 1024)
        CONSTRAINT PK_passenger PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    phone VARCHAR(64) NOT NULL
);

CREATE TABLE ticket
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 1024)
        CONSTRAINT PK_ticket PRIMARY KEY,
    fare NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(64) NOT NULL,
    expired BOOLEAN NOT NULL,
    trip_id INTEGER NOT NULL
        CONSTRAINT FK_trip REFERENCES trip (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ticket_passenger
(
    ticket_id INTEGER NOT NULL
        CONSTRAINT FK_ticket REFERENCES ticket (id)
            ON DELETE CASCADE ON UPDATE CASCADE,
    passenger_id INTEGER NOT NULL
        CONSTRAINT FK_passenger REFERENCES passenger (id)
            ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (ticket_id, passenger_id)
);


INSERT INTO trainline(name, phone)
VALUES
    ('RZD', '7-495-988-68-68'),
    ('LNER', '03457-225-333'),
    ('Amtrak', '1 215 856 7924');

INSERT INTO train(model, license_plate, trainline_id)
VALUES
    ('Roco 72787', '', 1),
    ('Roco 72693', '', 1),
    ('M62 1579', '', 1),
    ('Gresley', '', 2),
    ('GE P42DC', '', 3),
    ('Siemens ACS-64', '', 3);

INSERT INTO passenger(first_name, last_name, phone)
VALUES
    ('Ivan', 'Ivanov', '7-937-400-51-91'),
    ('Alex', 'Petrov', '8-996-800-05-90'),
    ('Joe', 'Biden', '1-202-456-1111'),
    ('Robert', 'Smith', '533-290-111');

INSERT INTO trip("from", "to", departure_time, arrival_time, train_id)
VALUES
    ('Moscow', 'Penza','2022-01-01:18:50:00'::timestamp, '2022-01-02:10:20:00'::timestamp, 2),
    ('Krasnadar', 'Kazan', '2022-03-25:02:10:00'::timestamp, '2022-03-25:21:30:00'::timestamp, 1),
    ('Vladivostok', 'Novosibirsk','2022-02-12:09:30:00'::timestamp, '2022-02-13:00:20:00'::timestamp, 3),
    ('New Jork', 'Washington','2022-01-06:10:45:00'::timestamp, '2022-01-06:18:15:00'::timestamp, 6),
    ('London', 'Paris', '2022-03-09:23:45:00'::timestamp, '2022-03-10:6:05:00'::timestamp, 4),
    ('California', 'San Diego', '2022-02-19:15:50:00'::timestamp, '2022-02-20:08:20:00'::timestamp, 5);

INSERT INTO ticket(fare, currency, expired, trip_id)
VALUES
    (2100, 'RUB', FALSE, 2),
    (1699, 'RUB', FALSE, 1),
    (170.99, 'EUR', FALSE, 4),
    (315.99, 'USD', FALSE, 5),
    (4500, 'RUB', FALSE, 3),
    (420.99, 'USD', FALSE, 6);

INSERT INTO ticket_passenger(ticket_id, passenger_id)
VALUES
    (3, 4),
    (5, 1),
    (2, 2),
    (1, 1),
    (4, 3),
    (6, 3);
