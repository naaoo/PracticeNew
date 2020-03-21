package com.company;

public class MemoryCard {

    String brand;
    String name;
    int size;
    int freeSpace = size;
    int savedPics = 0;

    public int getSavedPics() {
        return savedPics;
    }

    public void setSavedPics(int savedPics) {
        this.savedPics = savedPics;
    }

    public double getFreeSpacePerc() {
        double free = freeSpace;
        double sizeEmpty = size;
        double freePerc = ((free / sizeEmpty) * 100);
        return freePerc;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return brand + " " + name;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
