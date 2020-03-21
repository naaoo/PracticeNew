package com.company;

public class Guitarist extends Musician {

    public Guitarist(String name, int age) {
        super(name, age);
        super.instrument = Instrument.GUITAR;
    }

    @Override
    public String makeMusic() {
        return "guitar sound";
    }
}
