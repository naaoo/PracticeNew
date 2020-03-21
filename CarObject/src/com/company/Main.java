package com.company;

public class Main {

    public static void main(String[] args) {

        Car[] allCars = new Car[3];

        Car car0 = new Car();
        allCars[0] = car0;
        car0.setBrand("Toyota");
        car0.setType("Avensis");
        car0.setExactName("III Wagon 1.8");
        car0.setProductionYear(2010);
        car0.setColor("Red");
        car0.setHorsePower(147);
        car0.setFuelConsumption(6.6);
        car0.setTankVolume(60);
        car0.setTankFillingAtm(car0.getTankVolume());
        car0.setAutomatic(false);
        car0.setCabrio(false);
        car0.setMaxSpeed(200);

        Car car1 = new Car();
        allCars[1] = car1;
        car1.setBrand("Subaru");
        car1.setType("Impreza");
        car1.setExactName("I (GC) 2.0i");
        car1.setProductionYear(2000);
        car1.setColor("Blue");
        car1.setHorsePower(115);
        car1.setFuelConsumption(10.2);
        car1.setTankVolume(50);
        car1.setTankFillingAtm(car1.getTankVolume());
        car1.setAutomatic(false);
        car1.setCabrio(false);
        car1.setMaxSpeed(189);

        Car car2 = new Car();
        allCars[2] = car2;
        car2.setBrand("Mini");
        car2.setType("Cooper");
        car2.setExactName("S 2.0");
        car2.setProductionYear(2017);
        car2.setColor("Black");
        car2.setHorsePower(192);
        car2.setFuelConsumption(5.8);
        car2.setTankVolume(44);
        car2.setTankFillingAtm(car2.getTankVolume());
        car2.setAutomatic(true);
        car2.setCabrio(true);
        car2.setMaxSpeed(228);

        Car car3 = new Car();
        allCars[3] = car3;
        car3.setBrand("Ferrari");
        car3.setType("F8 Spider");
        car3.setExactName("2.9 V8");
        car3.setProductionYear(2020);
        car3.setColor("Yellow");
        car3.setHorsePower(720);
        car3.setFuelConsumption(14.5);
        car3.setTankVolume(78);
        car3.setTankFillingAtm(car3.getTankVolume());
        car3.setAutomatic(false);
        car3.setCabrio(true);
        car3.setMaxSpeed(340);



    }
}
