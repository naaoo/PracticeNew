package com.company;

import java.util.ArrayList;

public class Enclosure {
    EnclosureTypes barrier;
    int size;
    int spaces;
    Person keeper;
    boolean water;
    boolean topNet;
    ArrayList<Animal> animalsArr = new ArrayList<>();

    public static final int MIN_SIZE = 0;

    public Enclosure(Zoo zoo, EnclosureTypes enclosureType, int size, int spaces, Person keeper, boolean water, boolean topNet) {
        this.barrier = enclosureType;
        this.size = size;
        this.spaces = spaces;
        this.keeper = keeper;
        this.water = water;
        this.topNet = topNet;
        zoo.enclosureArr.add(this);
    }

    @Override
    public String toString() {
        return "Enclosure " + keeper;
    }
}
