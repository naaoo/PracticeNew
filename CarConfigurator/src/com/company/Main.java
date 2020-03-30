package com.company;

public class Main {

    public static void main(String[] args) {
        Wheels wheels1 = new Wheels("Pirelli", 17);
        Wheels wheels2 = new Wheels("Bridgestone", 18);

        Car mazda1 = new Car("Mazda", "CX-5", Color.RED, 160, wheels1);
        Car mazda2 = new Car("Mazda", "CX-5", Color.BLUE, 160, wheels2);
        Car ferrari1 = new Car("Ferrari", "F3", Color.RED, 320, wheels1);

        Car.carConfigurator();
        Car.printAllCars();
    }
}
