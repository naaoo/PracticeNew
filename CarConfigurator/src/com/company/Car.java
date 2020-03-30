package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Car implements Cloneable{
    String brand;
    String type;
    Color color;
    int horsePower;
    Wheels wheels;
    String serialNumber;
    static int ongoingNumber = 1; // adds unique number to the serial number
    static ArrayList<Car> carArr = new ArrayList<>();

    public Car(String brand, String type, Color color, int horsePower, Wheels wheels) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.horsePower = horsePower;
        try {
            Wheels newWheels = wheels.clone();
            this.wheels = newWheels;
        } catch(CloneNotSupportedException ex) {
            System.out.println("These wheels are not available at the moment...");
        }
        this.setSerialNumber();
        carArr.add(this);
    }

    public static void carConfigurator() {
        Scanner scanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Welcome to our Car Configurator!\nWhat car do you want? (Format: Brand Type)");
        String car = scanner.nextLine();
        String[] splittedString = car.split(" ");
        if (splittedString.length < 2) {
            System.out.println("Car not found. Please restart configurator");
            return;
        }
        String brand = splittedString[0];
        String type = splittedString[1];
        System.out.println("Color? (red, blue and green available)");
        String colorString = scanner.nextLine().substring(0,1).toLowerCase();
        Color color;
        switch (colorString) {
            case "r" : color = Color.RED; break;
            case "b" : color = Color.BLUE; break;
            case "g" : color = Color.GREEN; break;
            default : color = null; break;
        }
        System.out.println("Horse power?");
        int horsePower = intScanner.nextInt();
        // only two kind of tires existing ;-)
        System.out.println("Would you like Pirelli or Bridgestone tires? (p/b)");
        char wheelsChar = scanner.nextLine().charAt(0);
        Wheels wheels = null;
        try {
            switch (wheelsChar) {
                case 'p':
                case 'P':
                    wheels = Wheels.wheelsArr.get(0).clone();
                    break;
                case 'b':
                case 'B':
                    wheels = Wheels.wheelsArr.get(1).clone();
                    break;
            }
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        /* // (1) "normal" version: (Car is not cloned but generated)
        Car customCar = new Car(brand, type, color, horsePower, wheels);
        System.out.println("Your car has been configurated:");
        System.out.println(carArr.get(carArr.size() - 1));
         */

        // "Cloned" -version
        // (2) Checks if brand && type already existing in list (could be done for color/ps in the same way)
        boolean notExisting = true;
            for (Car existingCar : carArr) {
                if (existingCar.brand.equalsIgnoreCase(brand) && existingCar.type.equalsIgnoreCase(type)) {
                    try {
                    Car clonedCustomCar = existingCar.clone();
                    clonedCustomCar.setColor(color);
                    clonedCustomCar.setHorsePower(horsePower);
                    clonedCustomCar.setWheels(wheels);
                    clonedCustomCar.setSerialNumber();
                    carArr.add(clonedCustomCar);
                    System.out.println("Your car has been configurated:");
                    System.out.println(carArr.get(carArr.size() - 1) + "\n");
                    notExisting = false;
                    break;
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                }
            }
        }
        // (3) Clones whole car and set every parameter (only executed if brand && type isn't existing)
        // This code block would be better solved by (1) "normal version", but I guess that was the assignment..:
        if (notExisting) {
            try {
                Car clonedCustomCar = carArr.get(0).clone();
                clonedCustomCar.setBrand(brand);
                clonedCustomCar.setType(type);
                clonedCustomCar.setColor(color);
                clonedCustomCar.setHorsePower(horsePower);
                clonedCustomCar.setWheels(wheels);
                clonedCustomCar.setSerialNumber();
                carArr.add(clonedCustomCar);
                System.out.println("Your car has been configurated:");
                System.out.println(carArr.get(carArr.size() - 1) + "\n");
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setSerialNumber() {
        if (ongoingNumber < 10) {
            this.serialNumber = "X000" + ongoingNumber;
        } else if (ongoingNumber >= 10 && ongoingNumber < 100) {
            this.serialNumber = "X00" + ongoingNumber;
        } else if (ongoingNumber >= 100 && ongoingNumber < 1000) {
            this.serialNumber = "X0" + ongoingNumber;
        }
        ongoingNumber++;
        // and so on (needs to be changed if more than 10000 cars exist
    }

    public static void printAllCars() {
        for (Car car : carArr) {
            System.out.println(car);
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        return (Car)super.clone();
    }

    @Override
    public String toString() {
        return brand + " " + type + " (" + serialNumber + ", " + color + ", " + horsePower + "hP, " + wheels + " tires)";
    }
}
