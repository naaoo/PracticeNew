package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Department vorstand = new Department("Vorstand");
        Department einkauf = new Department("Einkauf");
        Department einkaufEuropa = new Department("Einkauf Europa");
        Department einkaufItalien = new Department("Einkauf Italien");
        Department einkaufUSA = new Department("Einkauf USA");
        Department vertrieb = new Department("Vertrieb");
        Department vertriebEuropa = new Department("Vertrieb Europa");

        File departmentFile = new File("C:\\Users\\David\\Documents\\nao\\Java-Programme\\Practice\\DepartmentsIO_1\\Abteilungen1.txt");
        try {
            FileReader fileReader = new FileReader(departmentFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedValues = line.split(";");
                String name = splittedValues[0];
                String departmentString = splittedValues[1];
                // if departmentString matches an department --> generate new Person in department
                for (Department department : Department.departments) {
                    if (department != null) {
                        if (department.name.equals(departmentString)) {
                            new Person(name, department);
                        }
                    }
                }
                if (splittedValues.length > 2) {
                    String parentDepartmentString = splittedValues[2];
                    // get subDepartment
                    Department parentDepartment = null;
                    Department subDepartment = null;
                    for (Department department : Department.departments) {
                        if (department != null) {
                            if (department.name.equals(departmentString)) {
                                subDepartment = department;
                            }
                        }
                    }
                    // get parentDepartment
                    for (Department department : Department.departments) {
                        if (department != null) {
                            if (department.name.equals(parentDepartmentString)) {
                                parentDepartment = department;
                            }
                        }
                    }
                    // set subDepartment in parentDepartments' Array
                    if (parentDepartment != null) {
                        parentDepartment.addSubDepartment(subDepartment);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception");
        }
        
        // Test:
        System.out.println();
        for (Person person : Person.persons) {
            if (person != null) {
                System.out.println(person.name + " " + person.department);
            }
        }
        // Test 2:
        System.out.println();
        vorstand.printStructure();
    }
}
