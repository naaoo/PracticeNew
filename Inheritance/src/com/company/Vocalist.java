package com.company;

public class Vocalist extends Musician {

    public Vocalist(String name, int age) {
        super(name, age);
        super.instrument = Instrument.VOICE;
    }

    @Override
    public String makeMusic() {
        return "vocal sound";
    }
}
