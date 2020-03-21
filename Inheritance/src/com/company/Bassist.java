package com.company;

public class Bassist extends Musician {

    public Bassist(String name, int age) {
        super(name, age);
        super.instrument = Instrument.BASS;
    }

    @Override
    public String makeMusic() {
        return "bass sound";
    }
}
