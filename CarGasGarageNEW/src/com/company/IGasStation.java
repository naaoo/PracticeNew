package com.company;

import java.io.IOException;

public interface IGasStation {
    public void fuelCar(Car car) throws IOException;
    public boolean fuelAvailable(Car car);
}
