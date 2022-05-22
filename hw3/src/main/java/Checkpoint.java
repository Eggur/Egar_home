import Animal.Parrot;

import java.util.*;
import java.util.stream.Collectors;

public class Checkpoint {
    public static void main(String[] args) {

        Scanner myName = new Scanner(System.in);
        System.out.println("Enter username");

        String username = myName.nextLine();
        System.out.println("Hello, " + username);

        List<Account> accounts = getAccount();

        //сортировка
        List<Account> sorted = accounts.stream()
                .sorted(Comparator.comparing(Account::getEmployee))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);



    }

    private static List<Account> getAccount(){
        return List.of(
                new Account("3921","Alex Buchavka"),
                new Account("9517","Mary Calet"),
                new Account("8462","John Frusciante"),
                new Account("2484","Antony Kidies")
        );
    }




}
