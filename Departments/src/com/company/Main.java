package com.company;

public class Main {

    public static void main(String[] args) {

        // Heads
        Person boss = new Person("Alfred", "Boss", "male");
        Person mustermann = new Person("Max", "Mustermann", "male");
        Person musterfrau = new Person("Angela", "Musterfrau", "female");
        Person muste = new Person("Alfons", "Muste", "male");
        Person kufmann = new Person("Alois", "Kufmann", "male");
        Person gunz = new Person("Herlinde", "Gunz", "female");
        Person friedrich = new Person("Hermann", "Friedrich", "male");
        Person peter = new Person("Hannelore", "Peter", "female");
        Person but = new Person("Moritz", "But", "male");

        // Employee
        Person stoss = new Person("Simon", "Stoss", "male");
        Person palm = new Person("Lina", "Palm", "female");
        Person bender = new Person("Fabio", "Bender", "male");
        Person schwender = new Person("Evelyn", "Schwender", "female");
        Person fitz = new Person("Dominik", "Fitz", "male");
        Person ritter = new Person("Tanja", "Ritter", "female");
        Person burkhart = new Person("Magdalena", "Burkhart", "female");

        Department company = new Department("Board", 2, 1, boss);

            Department sales = new Department("Sales", 2, 2, mustermann);
            company.subDepartments[0] = sales;

                Department salesPrivateClients = new Department("Sales Private Clients", 0, 3, musterfrau);
                company.subDepartments[0].subDepartments[0] = salesPrivateClients;

                Department salesBusinessClients = new Department("Sales Business Clients", 0, 3, muste);
                company.subDepartments[0].subDepartments[1] = salesBusinessClients;

            Department purchasing = new Department("Purchasing", 1, 2, kufmann);
            company.subDepartments[1] = purchasing;

                Department purchasingMechanics = new Department("Purchasing Mechanics", 2, 3, gunz);
                company.subDepartments[1].subDepartments[0] = purchasingMechanics;

                    Department purchasingSmallParts = new Department("Purchasing Small Parts", 0, 4, friedrich);
                    company.subDepartments[1].subDepartments[0].subDepartments[0] = purchasingSmallParts;

                    Department purchasingBigParts = new Department("Purchasing Big Parts", 1, 4, peter);
                    company.subDepartments[1].subDepartments[0].subDepartments[1] = purchasingBigParts;

                        Department purchasingEurope = new Department("Purchasing Europe", 0, 5, but);
                        company.subDepartments[1].subDepartments[0].subDepartments[1].subDepartments[0] = purchasingEurope;





        sales.addEmployee(stoss);
        sales.addEmployee(palm);
        /*
        salesBusinessClients.addEmployee(bender);
        salesPrivateClients.addEmployee(schwender);
        purchasing.addEmployee(fitz);
        purchasingSmallParts.addEmployee(ritter);
        purchasingEurope.addEmployee(burkhart);

        company.printStructure();

        sales.switchDepartment(0, purchasingBigParts);
        company.printStructure();

         */

        //company.printStructure();

        //sales.printGenderStats();

        //sales.switchDepartment(0, purchasingEurope);



        sales.addEmployee(bender);
        sales.addEmployee(fitz);

        //sales.getEmployee(2);

        Department.printStructure2(company);

        //System.out.println(sales.getEmployee(3));

        sales.switchDepartment(0, salesBusinessClients);

        System.out.println(sales.getEmployee(0));
        System.out.println(sales.getEmployee(1));
        System.out.println(sales.getEmployee(2));
        System.out.println(sales.getEmployee(3));

        /*for (int i = 0; i < sales.amountEmployee; i++) {
            System.out.println(sales.employeeArr[i]);
        }

         */

        Department.printStructure2(company);

        sales.printGenderStats();





    }
}
