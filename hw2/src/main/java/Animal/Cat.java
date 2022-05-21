package Animal;

public class Cat extends Animal implements IsPet {

    @Override
    public void makeSound() {
        System.out.println("meow");
    }

    @Override
    public void isPet() {
        System.out.println("of course i am a pet");
    }
}
