package com.company;

public class Main {

    public static void main(String[] args) {

        Services[] fullService = {Services.service, Services.tireChange};
        Services[] onlyService = {Services.service};
        Services[] onlyTires = {Services.tireChange};

        Driver manfred = new Driver("Manfred", 2000);
        Driver hubert = new Driver("Hubert", 120);

        Car bentley = new Car("Bentley", 260, 2400, 60, FuelTypes.gas, 12.6, manfred);
        Car mazda = new Car("Mazda", 120, 300, 40, FuelTypes.diesel, 8.2, hubert);

        GasStation avanti = new GasStation("Avanti", 500, 500, 1.114, 1.123);
        GasStation jet = new GasStation("Jet", 20, 10, 1.25, 1.31);

        Garage johnnys = new Garage("Johnnys", fullService, 50, 60);
        Garage billys = new Garage("Billys", onlyTires, 40, 0.0D);

        //bentley.printCarData();
        //bentley.drive(20);
        //bentley.refuelCar(avanti);

        mazda.printCarData();
        mazda.drive();
        johnnys.visitedBy(mazda);
        mazda.drive();
        jet.refuelCar(mazda);
        mazda.drive();
        mazda.drive();
        mazda.drive();


    }
}
