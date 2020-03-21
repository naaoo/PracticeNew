package com.company;

public class Department {

    String departmentName;
    int amountSubDepartments;
    Department[] subDepartments;
    Person head;
    int hierarchyLevel;
    int amountEmployee = 0;
    Person[] employeeArr = new Person[10];

    // constructor
    public Department(String departmentName, int amountSubDepartments, int hierarchyLevel, Person head) {
        this.departmentName = departmentName;
        this.amountSubDepartments = amountSubDepartments;
        this.subDepartments = new Department[amountSubDepartments];
        this.hierarchyLevel = hierarchyLevel;
        this.head = head;
    }

    // methods
    // print business structure
    public void printStructure () {
        System.out.println("Structure:");
        // 1st Level
        System.out.println("Boss: " + this.head);
        // 2nd Level
        for (int i = 0; i < this.subDepartments.length; i++) {
            System.out.println("\t|");
            System.out.println("\t" + this.subDepartments[i].departmentName + " (Head: " + this.subDepartments[i].head + ")");
            this.subDepartments[i].printEmployee();
            // 3rd Level
            for (int j = 0; j < this.subDepartments[i].subDepartments.length; j++) {
                System.out.println("\t\t|");
                System.out.println("\t\t" + this.subDepartments[i].subDepartments[j].departmentName + " (Head: " + this.subDepartments[i].subDepartments[j].head + ")");
                this.subDepartments[i].subDepartments[j].printEmployee();
                // 4th Level
                for (int k = 0; k < this.subDepartments[i].subDepartments[j].subDepartments.length; k++) {
                    System.out.println("\t\t\t|");
                    System.out.println("\t\t\t" + this.subDepartments[i].subDepartments[j].subDepartments[k].departmentName + " (Head: " + this.subDepartments[i].subDepartments[j].subDepartments[k].head + ")");
                    this.subDepartments[i].subDepartments[j].subDepartments[k].printEmployee();
                    // 5th Level
                    for (int l = 0; l < this.subDepartments[i].subDepartments[j].subDepartments[k].subDepartments.length; l++) {
                        System.out.println("\t\t\t\t|");
                        System.out.println("\t\t\t\t" + this.subDepartments[i].subDepartments[j].subDepartments[k].subDepartments[l].departmentName + " (Head: " + this.subDepartments[i].subDepartments[j].subDepartments[k].subDepartments[l].head + ")");
                        this.subDepartments[i].subDepartments[j].subDepartments[k].subDepartments[l].printEmployee();
                    }
                }
            }
        }
        System.out.println();
    }

    public static void printStructure2 (Department department) {
        String format = "";
        for (int i = 0; i < department.hierarchyLevel - 1; i++) {
            format = format + "\t";
        }
        System.out.println(format + "|");
        System.out.println(format + department.departmentName + " (Head: " + department.head + ")");
        department.printEmployee();
        for (int i = 0; i < department.subDepartments.length; i++) {
            printStructure2(department.subDepartments[i]);
        }
    }

    public void addEmployee (Person employee) {
        this.employeeArr[this.amountEmployee] = employee;
        this.amountEmployee++;
    }

    public void printEmployee() {
        if (this.amountEmployee > 0) {
            System.out.println("\t\t\t\t\tEmployee:");
            for (int i = 0; i < this.amountEmployee; i++) {
                System.out.println("\t\t\t\t\t\t" + this.employeeArr[i]);
            }
        }
    }

    public void subEmployee (int i) {
        for (int j = i; j < this.amountEmployee; j++) {
            this.employeeArr[j] = this.employeeArr[j + 1];
        }
        this.amountEmployee--;
    }

    public void switchDepartment (int employee, Department goalDepartment) {
        goalDepartment.addEmployee(this.employeeArr[employee]);
        this.subEmployee(employee);
    }

    public void printGenderStats () {
        double female = 0;
        double male = 0;
        int employee = 0;
        if (this.employeeArr[0] != null) {
            for (int i = 0; i < this.employeeArr.length; i++) {
                if (this.employeeArr[i] == null) {
                    break;
                }
                if (this.employeeArr[i].getGender().equals("female")) {
                    female++;
                } else if (this.employeeArr[i].getGender().equals("male")) {
                    male++;
                }
                employee++;
            }
        }
        System.out.println("\nGender stats:");
        System.out.println("Employee in department " + this.departmentName + ": " + employee);
        System.out.println("Female: " + female + " (" + ((female / employee) * 100) + "%)");
        System.out.println("Male: " + male + " (" + ((male / employee) * 100) + "%)");
    }

    // Getter and setter
    public Person getHead(Department department) {
        return head;
    }

    public void setHead(Person head) {
        this.head = head;
    }

    public int getAmountSubDepartments() {
        return amountSubDepartments;
    }

    public void setAmountSubDepartments(int amountSubDepartments) {
        this.amountSubDepartments = amountSubDepartments;
    }

    public Department[] getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(Department[] subDepartments) {
        this.subDepartments = subDepartments;
    }

    public Person getEmployee(int i) {
        return this.employeeArr[i];
    }

}
