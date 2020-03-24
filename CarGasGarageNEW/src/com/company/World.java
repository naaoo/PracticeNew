package com.company;

import java.util.Arrays;

public class World {
    // needed for iterating through all GasStations. There was some other way for iterating all instances of an object but couldn't find it... please help?
    public static GasStation[] allGasStations = new GasStation[0];
    int stationCounter = 0;

    public void addGasStation(GasStation gasStation) {
        this.allGasStations = Arrays.copyOf(this.allGasStations, this.stationCounter + 1);
        this.allGasStations[this.stationCounter] = gasStation;
        this.stationCounter++;
    }
}
