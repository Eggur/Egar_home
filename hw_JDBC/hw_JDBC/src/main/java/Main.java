import dao.PassengerDao2;
import entity.PassengerEntity;

public class Main {

    public static void main(String[] args) {

        PassengerDao2 passengerDao = PassengerDao2.getInstance();
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("///////////////////////////////////////////////////////////");

        System.out.println(passengerDao.findById(2));
        System.out.println("///////////////////////////////////////////////////////////");

        PassengerEntity passenger = new PassengerEntity();
        passenger.setFirstName("Egor");
        passenger.setLastName("Gurin");
        passenger.setPhone("79374005191");
        passengerDao.save(passenger);
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("////////////////////////////////////////////////////////////");

        passenger.setId(4);
        passengerDao.update(passenger);
        passengerDao.findAll().forEach(System.out::println);
        System.out.println("/////////////////////////////////////////////////////////////");

        passengerDao.delete(passenger);
        passengerDao.findAll().forEach(System.out::println);


    }
}
