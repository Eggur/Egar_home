import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<User> users = getUser();
        //сортировка по именам
        List<User> sorted = users.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println("___________________________");

        //сортировка по количеству заимствованных книг
        List<User> sorted1 = users.stream()
                .sorted(Comparator.comparing(User::getAmount))
                .collect(Collectors.toList());
        sorted1.forEach(System.out::println);

    }


    private static List<User> getUser(){
        return List.of(
                new User("John","Harry Potter","1"),
                new User("Alex","Idiot","3"),
                new User("Mary","War and Piace","2"),
                new User("Jim","Nausea","2"),
                new User("Andrew","1984","1"),
                new User("Mikael","Bible","5")
        );
    }
}
