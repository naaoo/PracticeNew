package com.company;

import jdk.nashorn.api.tree.FunctionExpressionTree;

import java.text.Format;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Car {

    String brand;
    int horsePower;
    int milage;
    int tankVolume;
    FuelTypes fuelType;
    double gasConsumption;
    Driver driver;

    public final int SERVICE_AFTER_KM = 500;
    public final int TIRES_MAX_MILAGE = 1000;

    int milageToService;
    double fuelLeft;
    int tiresLeft;

    public Car(String brand, int horsePower, int milage, int tankVolume, FuelTypes fuelType, double gasConsumption, Driver driver) {
        this.brand = brand;
        this.horsePower = horsePower;
        this.milage = milage;
        this.tankVolume = tankVolume;
        this.fuelType = fuelType;
        this.gasConsumption = gasConsumption;
        this.driver = driver;
        this.milageToService = SERVICE_AFTER_KM - (milage % SERVICE_AFTER_KM);
        this.fuelLeft = tankVolume;
        this.tiresLeft = TIRES_MAX_MILAGE - (milage % TIRES_MAX_MILAGE);
    }

    public void drive() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");
        int distance;
        while (true) {
            System.out.println("How far do you want to drive?");
            distance = scanner.nextInt();
            if (fuelLeft - (distance * (gasConsumption / 100)) < 0) {
                System.out.println("Your fuel won't last that long. (Max distance: " + f.format(fuelLeft / (gasConsumption / 100)) + "km)");
                continue;
            }
            break;
        }



        this.milageToService = milageToService - distance;
        this.tiresLeft = tiresLeft - distance;
        this.fuelLeft = fuelLeft - (distance * (gasConsumption / 100));
        System.out.println(this + " driving " + distance + "km...\n");
        this.printCarData();
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
        System.out.println("Fuel left: " + f.format(this.fuelLeft) + "l (" + f.format((fuelLeft / tankVolume) * 100) + "%)\n");
    }

    @Override
    public String toString() {
        return brand + " (Driver: " + driver + ")";
    }
}
