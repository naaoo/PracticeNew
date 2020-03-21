package com.company;

public class Enclosure {
    EnclosureTypes barrier;
    int size;
    int spaces;
    Person keeper;
    boolean water;
    boolean topNet;
    int amountAnimals = 0;
    Animal[] animalsArr = new Animal[100];

    public static final int MIN_SIZE = 0;

    public Enclosure(Zoo zoo, EnclosureTypes enclosureType, int size, int spaces, Person keeper, boolean water, boolean topNet) {
        this.barrier = enclosureType;
        this.size = size;
        this.spaces = spaces;
        this.keeper = keeper;
        this.water = water;
        this.topNet = topNet;
        zoo.enclosureArr[zoo.amountEnclosures] = this;
        zoo.amountEnclosures++;
    }

    @Override
    public String toString() {
        return "Enclosure " + keeper;
    }
}
