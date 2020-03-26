package com.company;

import java.util.ArrayList;

public class Department {
    String name;
    ArrayList<Person> employees = new ArrayList<>();
    ArrayList<Department> subDepartments = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();

    public Department(String name) {
        this.name = name;
        departments.add(this);

    }

    //Todo: print structure with indent
    public void printStructure () {
        System.out.println(this + " " + this.employees);
        for (int i = 0; i < this.subDepartments.size(); i++) {
            if (this.subDepartments != null) {
                this.subDepartments.get(i).printStructure();
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
