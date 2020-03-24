package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Car {
    String brand;
    Person driver;
    Location location;
    Fuel fuelType;
    double tankVolume;
    double consumption; //per 100km
    int milage;
    int milageToService;
    double fuelLeft;
    int tiresLeft;
    public final int SERVICE_AFTER_KM = 500;
    public final int TIRES_MAX_MILAGE = 1000;

    public Car(String brand, Person driver, Location location, Fuel fuelType, double tankVolume, double consumtion, int milage) {
        this.brand = brand;
        this.driver = driver;
        this.location = location;
        this.fuelType = fuelType;
        this.tankVolume = tankVolume;
        this.consumption = consumtion;
        this.milage = milage;
        this.milageToService = SERVICE_AFTER_KM - (milage % SERVICE_AFTER_KM);
        this.fuelLeft = tankVolume;
        this.tiresLeft = TIRES_MAX_MILAGE - (milage % TIRES_MAX_MILAGE);
    }

    public void driveCertainKm() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");
        int distance;
        while (true) {
            System.out.println("How far do you want to drive?");
            distance = scanner.nextInt();
            if (fuelLeft - (distance * (consumption / 100)) < 0) {
                System.out.println("Your fuel won't last that long. (Max distance: " + f.format(fuelLeft / (consumption / 100)) + "km)");
                continue;
            }
            break;
        }
        this.drive(distance);
        this.printCarData();
    }

    public void driveTo(Location location) {
        DecimalFormat f = new DecimalFormat("#0.00");
        int distance = this.getDistance(location);
        if (fuelLeft - (distance * (consumption / 100)) < 0) {
            System.out.println("Your fuel won't last that long. (" + distance + "km to " + location + ", Max distance: " + f.format(fuelLeft / (consumption / 100)) + "km)");
        } else {
            this.drive(distance);
            this.location = location;
        }
    }

    public void drive(int distance) {
        this.milageToService = milageToService - distance;
        this.tiresLeft = tiresLeft - distance;
        this.fuelLeft = fuelLeft - (distance * (consumption / 100));
        System.out.println("\n" + this + " driving " + distance + "km...");
    }

    public void printCarData() {
        DecimalFormat f = new DecimalFormat("#0.00");
        System.out.println(this);
        if (this.milageToService < 50) {
            if(this.milageToService < 0) {
                System.out.println("WARNING!!! SERVICE IS OVERDUE!!!");
            } else {
                System.out.println("WARNING! SERVICE IS NEEDED!");
            }
        }
        System.out.println("Service in: " + this.milageToService + "km");
        if (this.tiresLeft < 50) {
            if (this.tiresLeft < 0) {
                System.out.println("WARNING!!! TIRECHANGE IS OVERDUE!!!");
            } else {
                System.out.println("WARNING! TIRECHANGE IS NEEDED!");
            }
        }
        System.out.println("Tirechange in: " + this.tiresLeft + "km");
        if (((this.fuelLeft / this.tankVolume) * 100) < 10) {
            System.out.println("WARNING! FUEL REFILL IS NEEDED!");
        }
        if (this.fuelType == Fuel.ELECTRICITY) {
            System.out.println("Fuel left: " + f.format(this.fuelLeft) + " units (" + f.format((fuelLeft / tankVolume) * 100) + "%)\n");
        } else {
            System.out.println("Fuel left: " + f.format(this.fuelLeft) + "l (" + f.format((fuelLeft / tankVolume) * 100) + "%)\n");
        }
    }

    public GasStation searchBestStation() {
        GasStation bestStation = null;
        int smallestDistance = Integer.MAX_VALUE;
        for (GasStation gasStation : World.allGasStations) {
            if (gasStation.fuelAvailable(this)) {
                int distance = this.getDistance(gasStation.location);
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    bestStation = gasStation;
                }
            }
        }
        System.out.println("\n" + bestStation + " (Distance: " + smallestDistance + "km) is the best station for " + this);
        return bestStation;
    }

    public int getDistance(Location location) {
        int distance = 0;
        switch (this.location) {
            case DORNBIRN :
                switch (location) {
                    case DORNBIRN : distance = 0; break;
                    case FELDKIRCH : distance = 30; break;
                    case BLUDENZ : distance = 50; break;
                } break;
            case BLUDENZ :
                switch (location) {
                    case DORNBIRN : distance = 50; break;
                    case FELDKIRCH : distance = 20; break;
                    case BLUDENZ : distance = 0; break;
                } break;
            case FELDKIRCH :
                switch (location) {
                    case DORNBIRN : distance = 30; break;
                    case FELDKIRCH : distance = 0; break;
                    case BLUDENZ : distance = 20; break;
                } break;
        }
        return distance;
    }

    @Override
    public String toString() {
        return brand + " (Driver: " + driver + ", Location: " + location + ") ";
    }
}
