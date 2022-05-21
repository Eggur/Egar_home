package com.eggur.example;


public class Car {
    private String Color;
    private String Form;
    private double engineV;
    private double fuelConsumption;
    private String id;
    private double fuelInTank;

    public Car(){
        fuelInTank = 10;
        id = "aa555b";
    }

    public Car(String color, String form, double engineV,double fuelConsumption, String id, double fuelInTank){
        this.Color = color;
        this.Form = form;
        this.engineV = engineV;
        this.fuelConsumption = fuelConsumption;
        this.id = id;
        this.fuelInTank = fuelInTank;
    }



    public void fillTank(int fuelV) {
        fuelInTank = fuelInTank + fuelV;}
    public void drive(double distance) {
        fuelInTank = fuelInTank - distance/100*fuelConsumption;}

    public double getFuelInTank(){
        return fuelInTank;
    }

}
