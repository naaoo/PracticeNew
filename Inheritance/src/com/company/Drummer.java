package com.company;

public class Drummer extends Musician {

    public Drummer(String name, int age) {
        super(name, age);
        super.instrument = Instrument.DRUMS;
    }

    @Override
    public String makeMusic() {
        return "drum sound";
    }
}
