package entity;

import lombok.Data;

@Data
public class TrainEntity {

    private Integer id;
    private String model;
    private String licensePlate;
    private TrainlineEntity trainlineEntity;

}
