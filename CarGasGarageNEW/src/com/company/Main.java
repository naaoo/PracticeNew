package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // ServiceStation = mother class of GasStation & Garage
        // treat(car) is applicable to all ServiceStations
        // car can searchBestStation (closest GasStation that has needed fuel)
        // car can driveCertainKm (but location doesn't change ;-) )
        // car can driveTo(location)
        // --> always checked if distance is possible (enough fuel left)
        // Warning in printCarData if service, tirechange or fuel are needed (but still able to drive unless fuel is too little)
        //Garages can change tires, do service or both

        World world = new World();
        Person elon = new Person("Elon", 200);
        Person hans = new Person("Hans", 100);
        Car tesla = new Car("Tesla", elon, Location.FELDKIRCH, Fuel.ELECTRICITY, 30, 8.2, 200);
        Car bentley = new Car("Bentley", hans, Location.DORNBIRN, Fuel.GAS, 60, 12.2, 600);
        Fuel[] everything = {Fuel.GAS, Fuel.DIESEL, Fuel.ELECTRICITY};
        Fuel[] noElectricity = {Fuel.GAS, Fuel.DIESEL};
        Fuel[] onlyElectricity = {Fuel.ELECTRICITY};
        GasStation jet = new GasStation("Jet", Location.DORNBIRN, everything, 1.113, 1.123, 0.6, world);
        GasStation avanti = new GasStation("Avanti", Location.BLUDENZ, noElectricity, 1.101, 1.21, 0.0, world);
        GasStation vkw = new GasStation("VKW", Location.FELDKIRCH, onlyElectricity, 0.0, 0.0, 0.55, world);
        Garage johnnys = new Garage("Johnnys", Location.FELDKIRCH, 50, 60);
        Garage billys = new Garage("Billys", Location.DORNBIRN, 40, 120);

        tesla.printCarData();
        tesla.driveCertainKm();
        tesla.driveTo(Location.DORNBIRN);
        avanti.treat(tesla);
        tesla.searchBestStation().treat(tesla);
        billys.treat(tesla);

    }
}
