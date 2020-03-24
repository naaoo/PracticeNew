package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Garage extends ServiceStation {
    double priceTireChange;
    double priceService;
    double partBill = 0.0D;

    public Garage(String name, Location location, double priceTireChange, double priceService) {
        super.name = name;
        super.location = location;
        this.priceTireChange = priceTireChange;
        this.priceService = priceService;
    }

    @Override
    public void treat(Car car) {
        visitedBy(car);
    }

    public void visitedBy(Car car) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");

        car.driveTo(this.location);
        System.out.println("Welcome to " + this.name + " (" + this.location + ")!\nWhat can we do for you? (Service / Tire Change / Both)");
        String wantedService = scanner.nextLine();
        if (wantedService.substring(0, 1).equalsIgnoreCase("s")) {
            doService(car);
        } else if (wantedService.substring(0, 1).equalsIgnoreCase("t")) {
            changeTires(car);
        } else if (wantedService.substring(0, 1).equalsIgnoreCase("b")) {
            doService(car);
            changeTires(car);
        }
        System.out.println("Your bill is: " + partBill + "€\nThank you for your visit!");
        car.driver.money = car.driver.money - partBill;
        System.out.println("You have " + f.format(car.driver.money) + "€ left\n");
        car.printCarData();
        this.partBill = 0.0D;
    }

    public void changeTires(Car car) {
        if (car.driver.money < (partBill + this.priceTireChange)) {
            System.out.println("Sorry but you won't be able to pay for the tire change...");
            return;
        } else {
            System.out.println("Changing tires... Completed.");
            car.tiresLeft = car.TIRES_MAX_MILAGE;
            this.partBill = this.partBill + this.priceTireChange;
        }
    }

    public void doService(Car car) {
        if (car.driver.money < (partBill + this.priceService)) {
            System.out.println("Sorry but you won't be able to pay for the service...");
            return;
        } else {
            System.out.println("Servicing car... Completed.");
            car.milageToService = car.SERVICE_AFTER_KM;
            this.partBill = this.partBill + this.priceService;
        }
    }
}
