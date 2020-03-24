package com.company;

public abstract class ServiceStation {
    String name;
    Location location;

    public abstract void treat(Car car);
}
