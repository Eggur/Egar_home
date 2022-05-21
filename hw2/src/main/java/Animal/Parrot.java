package Animal;

import Animal.Animal;

public class Parrot extends Animal implements IsPet, IsWild {

    public String name;

    public void sayHello(){
        System.out.println(name);
    }

    @Override
    public void makeSound() {
        System.out.println("<sings a song>");
    }

    @Override
    public void isPet() {
        System.out.println("yes, i am a pet");
    }

    @Override
    public void isWild() {
        System.out.println("but i can also be wild");
    }
}
