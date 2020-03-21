package com.company;


import java.util.Arrays;

public class World {
    public static GasStation[] allGasStations = new GasStation[0];
    int stationCounter = 0;

    public void addGasStation(GasStation gasStation) {
        //needs to be init in GasStation
        this.allGasStations = Arrays.copyOf(this.allGasStations, this.stationCounter + 1);
        this.allGasStations[this.stationCounter] = gasStation;
        this.stationCounter++;
    }
}
