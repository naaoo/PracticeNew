package com.company;

public class Main {

    public static void main(String[] args) {
        World world = new World();
        Fuel[] everything = {Fuel.GASOLINE, Fuel.DIESEL, Fuel.ELECTRICITY, Fuel.GAS};
        Fuel[] noElectricity = {Fuel.GASOLINE, Fuel.DIESEL, Fuel.GAS};
        Fuel[] onlyElectricity = {Fuel.ELECTRICITY};
        GasStation jet = new GasStation("Jet", Location.DORNBIRN, everything, world);
        GasStation avanti = new GasStation("Avanti", Location.BLUDENZ, noElectricity, world);
        GasStation vkw = new GasStation("VKW", Location.FELDKIRCH, onlyElectricity, world);
        Car ferrari = new Car("Ferrari", Fuel.GASOLINE, Location.DORNBIRN);
        Car tesla = new Car("Tesla", Fuel.ELECTRICITY, Location.BLUDENZ);

        avanti.fuelCar(ferrari);
        avanti.fuelCar(tesla);
        tesla.searchBestStation().fuelCar(tesla);
    }
}
