package com.company;

public class Lens {
    String brand;
    String name;
    int focalLengthMin = 0;
    int focalLengthMax = 0;

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setFocalLength(int focalLengthMin, int focalLengthMax) {
        if (focalLengthMin > focalLengthMax) {
            System.out.println("minimal focal length can't be higher than maximal focal length");
        } else {
            this.focalLengthMin = focalLengthMin;
            this.focalLengthMax = focalLengthMax;
        }
    }
    public int getFocalLengthMin() {
        return focalLengthMin;
    }
    public int getFocalLengthMax() {
        return focalLengthMax;
    }

    public String getFocalRange() {
        return getFocalLengthMin() + " to " + getFocalLengthMax();
    }

    @Override
    public String toString() {
        return brand + ' ' + name; }
}
