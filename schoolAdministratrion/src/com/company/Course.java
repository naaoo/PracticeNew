package com.company;

import java.sql.*;

public class Course {

    int id;
    String name;
    int maxSeats;
    Person teacher;

    public Course(int id, String name, int maxSeats, Person teacher) {
        this.id = id;
        this.name = name;
        this.maxSeats = maxSeats;
        this.teacher = teacher;
    }
}
