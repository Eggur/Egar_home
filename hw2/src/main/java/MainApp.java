import Animal.Cat;
import Animal.Elk;
import Animal.Parrot;

public class MainApp {
    public static void main(String[] args) {

        Cat cat = new Cat();
        Parrot parrot = new Parrot();
        Elk elk = new Elk();

        cat.makeSound();
        cat.isPet();

        parrot.makeSound();
        parrot.isPet();
        parrot.isWild();

        elk.makeSound();
        elk.isWild();
    }

}
