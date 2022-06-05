package entity;

import lombok.Data;

@Data
public class TicketPassengerEntity {
    private TicketEntity ticketEntity;
    private PassengerEntity passengerEntity;
}
