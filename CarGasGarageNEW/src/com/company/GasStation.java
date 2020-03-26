package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class GasStation extends ServiceStation implements IGasStation{
    Fuel[] availableFuel;
    double priceGas;
    double priceElec;
    double priceDiesel;

    public GasStation(String name, Location location, Fuel[] availableFuel, double priceGas, double priceDiesel, double priceElec, World world)  {
        super.name = name;
        super.location = location;
        this.availableFuel = availableFuel;
        this.priceGas = priceGas;
        this.priceDiesel = priceDiesel;
        this.priceElec = priceElec;
        world.addGasStation(this);
    }

    @Override
    public void treat(Car car) throws IOException {
        car.driveTo(this.location);
        if (car.location == this.location) {
            fuelCar(car);
        }
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

    @Override
    public void fuelCar(Car car) throws IOException {
        Scanner Scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");
        Double filling;
        Double price = 0.0D;
        Double bill;
        boolean fuelAvailable = this.fuelAvailable(car);
        if (!fuelAvailable) {
            System.out.println(this + ": Sorry but we don't have the kind of fuel you need...");
            return;
        }
        // get price
        if (car.fuelType == Fuel.GAS) {
            price = this.priceGas;
        } else if (car.fuelType == Fuel.DIESEL) {
            price = this.priceDiesel;
        } else if (car.fuelType == Fuel.ELECTRICITY) {
            price = this.priceElec;
        }
        System.out.println("Welcome to " + this + "!\nWe detected that your car is using " + car.fuelType + ".");
        while (true) {
            // get wanted filling
            System.out.println("Full fill up: " + f.format(car.tankVolume - car.fuelLeft) + "l");
            if (car.fuelType == Fuel.ELECTRICITY) {
                System.out.println("How much would you like to tank? (amount electricity units / full)");
            } else {
                System.out.println("How much would you like to tank? (amount litres / full)");
            }
            String fill = Scanner.nextLine();
            if (fill.substring(0, 1).equalsIgnoreCase("f")) {
                filling = car.tankVolume - car.fuelLeft;
            } else {
                filling = Double.parseDouble(fill);
            }
            // filling higher than open tank
            if (filling > (car.tankVolume - car.fuelLeft)) {
                System.out.println("Not enough open tank volume to fuel that amount, please enter a lower amount. (Max possible: " + f.format(car.tankVolume - car.fuelLeft) + "l)");
                continue;
            }
            // get bill
            bill = filling * price;
            System.out.println("Your bill will be: " + f.format(bill) + "€");
            if (bill > car.driver.money) {
                System.out.println("This exceeds your available funds. Can't refill. (Max possible: " + f.format(car.driver.money / price) + "l)");
                continue;
            }
            writeReceipt(car, "Fuel", bill);
            System.out.println("Filling... Complete.");
            break;
        }
        car.driver.money = (car.driver.money - bill);
        car.fuelLeft = car.fuelLeft + filling;
        System.out.println("You have " + f.format(car.driver.money) + "€ left.\nThank you for using our service!\n");
        car.printCarData();
    }

    // check if fuel is available in GasStation
    @Override
    public boolean fuelAvailable(Car car) {
        boolean fuelAvailable = false;
        for (Fuel fuel : this.availableFuel) {
            if (fuel == car.fuelType) {
                fuelAvailable = true;
            }
        }
        return fuelAvailable;
    }

    @Override
    public String toString() {
        return name + " " + location;
    }
}
