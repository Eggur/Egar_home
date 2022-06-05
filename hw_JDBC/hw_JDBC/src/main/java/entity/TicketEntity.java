package entity;

import lombok.Data;

@Data
public class TicketEntity {

    private Integer id;
    private Double fare;
    private String currency;
    private Boolean expired;
    private TripEntity tripEntity;

}
