package com.eggur.example;

public class MainClass {
    public static void main(String[] args) {
        Car myCar = new Car("red","sedan",2.5 ,10,"rare",200);
        Car myBrotherCar = new Car();
        myCar.fillTank(20);

        System.out.println("current fuel level = " + myCar.getFuelInTank());

    }
}
