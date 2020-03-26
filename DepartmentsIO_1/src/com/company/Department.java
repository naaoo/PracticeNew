package com.company;

public class Department {
    String name;
    Department[] subDepartments = new Department[10];
    int subDepartmentCounter = 0;
    public static Department[] departments = new Department[20];
    public static int departmentCounter = 0;

    public Department(String name) {
        this.name = name;
        addDepartment(this);

    }

    public void addDepartment(Department department) {
        departments[departmentCounter] = department;
        departmentCounter++;
    }

    public void addSubDepartment(Department department) {
        subDepartments[subDepartmentCounter] = department;
        subDepartmentCounter++;
    }

    //Todo: print structure with ident
    public void printStructure () {
        System.out.println(this.name);
        for (int i = 0; i < this.subDepartmentCounter; i++) {
            if (this.subDepartments[i] != null) {
                this.subDepartments[i].printStructure();
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
