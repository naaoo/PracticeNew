package com.company;

public class Department {
    String name;

    public static Department[] departments = new Department[20];
    public static int departmentCounter = 0;

    public Department(String name) {
        this.name = name;
        addDepartment(this);

    }

    public void addDepartment(Department department) {
        departments[departmentCounter] = this;
        departmentCounter++;
    }

    @Override
    public String toString() {
        return name;
    }
}
