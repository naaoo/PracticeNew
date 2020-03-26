package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public void treat(Car car) throws IOException {
        visitedBy(car);
    }

    @Override
    public void writeReceipt(Car car, String service, double cost) throws IOException {
        try {
            File receipt = new File("C:\\Users\\David\\Documents\\nao\\Java-Programme\\Practice\\CarGasGarageNEW\\Receipt.txt");
            FileWriter myWriter = new FileWriter(receipt, true);
            myWriter.write("\n" + car.driver + ";" + car + ";" + service + ";" + cost);
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

    public void visitedBy(Car car) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");

        car.driveTo(this.location);
        System.out.println("Welcome to " + this.name + " (" + this.location + ")!\nWhat can we do for you? (Service / Tire Change / Both)");
        String wantedService = scanner.nextLine();
        if (wantedService.substring(0, 1).equalsIgnoreCase("s")) {
            doService(car);
            showBill(car, f);
            writeReceipt(car, "service", partBill);
        } else if (wantedService.substring(0, 1).equalsIgnoreCase("t")) {
            changeTires(car);
            showBill(car, f);
            writeReceipt(car, "tires", partBill);
        } else if (wantedService.substring(0, 1).equalsIgnoreCase("b")) {
            doService(car);
            changeTires(car);
            showBill(car, f);
            writeReceipt(car, "service + tires", partBill);
        }

        car.printCarData();
        this.partBill = 0.0D;
    }

    private void showBill(Car car, DecimalFormat f) {
        System.out.println("Your bill is: " + partBill + "€\nThank you for your visit!");
        car.driver.money = car.driver.money - partBill;
        System.out.println("You have " + f.format(car.driver.money) + "€ left\n");
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
