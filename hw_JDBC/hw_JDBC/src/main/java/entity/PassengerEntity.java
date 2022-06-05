package entity;

import lombok.Data;

import java.util.List;

@Data
public class PassengerEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private List<TicketEntity> ticketEntityList;


}
