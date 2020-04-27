package com.company.database.model;

public class Course {

    public Integer id;
    public String name;
    public int maxSeats;
    public Person teacher;

    public Course(Integer id, String name, int maxSeats, Person teacher) {
        this.id = id;
        this.name = name;
        this.maxSeats = maxSeats;
        this.teacher = teacher;
    }
}
