package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GasStation {
    String name;
    double availableGas;
    double availableDiesel;
    double priceGas;
    double priceDiesel;

    public GasStation(String name, double availableGas, double availableDiesel, double priceGas, double priceDiesel) {
        this.name = name;
        this.availableGas = availableGas;
        this.availableDiesel = availableDiesel;
        this.priceGas = priceGas;
        this.priceDiesel = priceDiesel;
    }

    public void refuelCar(Car car) {
        Scanner Scanner = new Scanner(System.in);
        DecimalFormat f = new DecimalFormat("#0.00");
        Double filling;
        Double price = 0.0D;
        Double fuelAvailable = 0.0D;
        Double bill;

        // get price and available fuel amount
        if (car.fuelType == FuelTypes.gas) {
            price = this.priceGas;
            fuelAvailable = this.availableGas;
        } else if (car.fuelType == FuelTypes.diesel) {
            price = this.priceDiesel;
            fuelAvailable = this.availableDiesel;
        }
        System.out.println("Welcome to " + this + "!\nWe detected that your car is using " + car.fuelType + ".");
        while (true) {
            // get wanted filling
            System.out.println("Full fill up: " + f.format(car.tankVolume - car.fuelLeft) + "l");
            System.out.println("How much would you like to tank? (amount litres / full)");
            String fill = Scanner.nextLine();
            if (fill.substring(0, 1).equalsIgnoreCase("f")) {
                filling = car.tankVolume - car.fuelLeft;
            } else {
                filling = Double.parseDouble(fill);
            }
            // Not enough fuel in station
            if (filling > fuelAvailable) {
                System.out.println("This exceeds our supply, please enter a lower amount. (Max available: " + fuelAvailable + "l)");
                continue;
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
            System.out.println("Filling... Complete.");
            break;
        }
        car.driver.money = (car.driver.money - bill);
        car.fuelLeft = car.fuelLeft + filling;
        System.out.println("You have " + f.format(car.driver.money) + "€ left.\nThank you for using our service!\n");
        car.printCarData();
    }

    @Override
    public String toString() {
        return name;
    }
}
