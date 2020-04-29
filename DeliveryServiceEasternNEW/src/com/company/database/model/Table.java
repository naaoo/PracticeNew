package com.company.database.model;

public class Table implements Cloneable{
    public Integer id;
    public int maxSeats;

    public Table(Integer id, int maxSeats) {
        this.id = id;
        this.maxSeats = maxSeats;
    }

    @Override
    public Table clone() throws CloneNotSupportedException {
        return (Table)super.clone();
    }
}
