package com.company;

public class Car {
    String name;
    Fuel usedFuel;
    Location location;

    public Car(String name, Fuel usedFuel, Location location) {
        this.name = name;
        this.usedFuel = usedFuel;
        this.location = location;
    }

    public GasStation searchBestStation() {
        GasStation bestStation = null;
        int smallestDistance = Integer.MAX_VALUE;
        for (GasStation gasStation : World.allGasStations) {
            if (gasStation.fuelAvailable(this)) {
                int distance = this.getDistance(gasStation);
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    bestStation = gasStation;
                }
            }
        }
        System.out.println(bestStation + " (Distance: " + smallestDistance + "km) is the best station for " + this);
        return bestStation;
    }

    public int getDistance(GasStation gasStation) {
        int distance = 0;
        switch (this.location) {
            case DORNBIRN :
                switch (gasStation.location) {
                    case DORNBIRN : distance = 0; break;
                    case FELDKIRCH : distance = 30; break;
                    case BLUDENZ : distance = 50; break;
                } break;
            case BLUDENZ :
                switch (gasStation.location) {
                    case DORNBIRN : distance = 50; break;
                    case FELDKIRCH : distance = 30; break;
                    case BLUDENZ : distance = 0; break;
                } break;
            case FELDKIRCH :
                switch (gasStation.location) {
                    case DORNBIRN : distance = 30; break;
                    case FELDKIRCH : distance = 0; break;
                    case BLUDENZ : distance = 50; break;
                } break;
        }
        return distance;
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}
