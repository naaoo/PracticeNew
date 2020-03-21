package com.company;

public class Car {
    String brand;
    String type;
    String exactName;
    String color;
    int productionYear;
    int horsePower;
    double fuelConsumption;         //per 100km
    double tankVolume;
    double tankFillingAtm;
    boolean automatic;
    boolean cabrio;
    int maxSpeed;

    public void setBrand(String brand) { this.brand = brand; }
    public String getBrand() {return brand; }

    public void setType(String type) { this.type = type; }
    public String getType() {return type; }

    public void setExactName(String type) { this.exactName = exactName; }
    public String getExactName() {return exactName; }

    public void setColor(String color) { this.color = color; }
    public String getColor() {return color; }

    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }
    public int getProductionYear() {return productionYear; }

    public void setHorsePower(int horsePower) { this.horsePower = horsePower; }
    public int getHorsePower() {return horsePower; }

    public void setFuelConsumption(double fuelConsumption) { this.fuelConsumption = fuelConsumption; }
    public double getFuelConsumption() {return fuelConsumption; }

    public void setTankVolume(double tankVolume) { this.tankVolume = tankVolume; }
    public double getTankVolume() {return tankVolume; }

    public void setTankFillingAtm(double tankFillingAtm) { this.tankFillingAtm = tankFillingAtm; }
    public double getTankFillingAtm() {return tankFillingAtm; }

    public void setAutomatic(boolean automatic) { this.automatic = automatic; }
    public boolean getAutomatic() { return automatic; }

    public void setCabrio(boolean cabrio) { this.cabrio = cabrio; }
    public boolean getCabrio() { return cabrio; }

    public void setMaxSpeed(int maxSpeed) {this.maxSpeed = maxSpeed; }
    public int getMaxSpeed() { return maxSpeed; }

    public static void printDataSheet(Car[] allCars, int i) {
        System.out.println(allCars[i].getBrand() + " " + allCars[i].getType() + " " + allCars[i].getExactName());
        System.out.println("Color: " + allCars[i].getColor());
        System.out.println("Production year: " + allCars[i].getProductionYear());
        System.out.println("kwh: " + allCars[i].getHorsePower());
        System.out.println("Tank volume: " + allCars[i].getTankVolume());
        System.out.println("Fuel consumption: " + allCars[i].getFuelConsumption());
        System.out.println("Tank filling (at the moment): " + allCars[i]. getTankFillingAtm());
        if (allCars[i].getAutomatic()) {
            System.out.println("Automatic shift");
        } else {
            System.out.println("Stick shift");
        }
        if (allCars[i].getCabrio()) {
            System.out.println("Convertible hood");
        } else {
            System.out.println("No convertible hood");
        }
    }






}
