package com.company;

import java.io.IOException;

public abstract class ServiceStation {
    String name;
    Location location;

    public abstract void treat(Car car) throws IOException;

    public abstract void writeReceipt(Car car, String service, double cost) throws IOException;
}
