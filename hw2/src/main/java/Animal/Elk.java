package Animal;

public class Elk extends Animal implements IsWild {

    @Override
    public void makeSound() {
        System.out.println("woo?<idk what sound do they make>");
    }

    @Override
    public void isWild() {
        System.out.println("I am a wild animal");
    }
}
