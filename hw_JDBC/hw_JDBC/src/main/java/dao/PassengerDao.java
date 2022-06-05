package dao;

import entity.PassengerEntity;

import java.util.List;
import java.util.Optional;

public interface PassengerDao {

    List<PassengerEntity> findAll();

    Optional<PassengerEntity> findById(Integer id);

    void save(PassengerEntity passenger);

    void update(PassengerEntity passenger);

    void delete(PassengerEntity passenger);

}
