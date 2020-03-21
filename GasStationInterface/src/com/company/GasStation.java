package com.company;

public class GasStation implements IGasStation{
    String name;
    Location location;
    Fuel[] availableFuel;

    public GasStation(String name, Location location, Fuel[] availableFuel, World world) {
        this.name = name;
        this.location = location;
        this.availableFuel = availableFuel;
        world.addGasStation(this);
    }

    @Override
    public boolean fuelAvailable(Car car) {
        boolean fuelAvailable = false;
        for (Fuel fuel : this.availableFuel) {
            if (fuel == car.usedFuel) {
                fuelAvailable = true;
            }
        }
        return fuelAvailable;
    }

    @Override
    public void fuelCar(Car car) {
        boolean fuelAvailable = this.fuelAvailable(car);
        if (fuelAvailable) {
            car.location = this.location;
            System.out.println(this + ": " + car + " has been fueled");
        } else {
            System.out.println(this + ": Sorry, but we don't have the kind of fuel " + car + " needs");
        }
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}
