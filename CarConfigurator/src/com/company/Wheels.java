package com.company;

import java.util.ArrayList;

public class Wheels implements Cloneable{
    String name;
    int inch;
    public static ArrayList<Wheels> wheelsArr = new ArrayList<>();

    public Wheels(String name, int inch) {
        this.name = name;
        this.inch = inch;
        wheelsArr.add(this);
    }

    @Override
    protected Wheels clone() throws  CloneNotSupportedException {
        return (Wheels)super.clone();
    }

    @Override
    public String toString() {
        return name;
    }
}
