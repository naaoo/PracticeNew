package com.company;

public class Zoo {
    Person head;
    String location;
    int amountEnclosures = 0;
    int amountAnimals = 0;
    int amountEmployee = 0;
    Enclosure[] enclosureArr = new Enclosure[100];
    Person[] employeeArr = new Person[100];

    public Zoo(String location) {
        this.location = location;
    }

    // methods
    //Print zoo data
    public void printZooData() {
        System.out.println("Zoo: " + this.location);
        System.out.println("Head: " + this.head);
        System.out.println("Animals (Overall): " + this.amountAnimals);
        System.out.println("Enclosures: " + this.amountEnclosures);
        System.out.println("Employees: " + this.amountEmployee);
        System.out.println();
    }
    public void countAnimalsSpecies(String species) {
        int counterOvr = 0;
        System.out.println("Animal count: species " + species);
        for (int i = 0; i < amountEnclosures; i++) {
            int counterEnc = 0;
            for (int j = 0; j < enclosureArr[i].amountAnimals; j++) {
                if (enclosureArr[i].animalsArr[j].species.equalsIgnoreCase(species)) {
                    counterEnc++;
                    counterOvr++;
                }
            }
            System.out.println("\t" + enclosureArr[i] + ": " + counterEnc);
        }
        System.out.println(species + "s overall: " + counterOvr + "\n");
    }

    public void countAnimalsGroup(String group) {
        int counterOvr = 0;
        System.out.println("Animal count: group " + group + " animals");
        for (int i = 0; i < amountEnclosures; i++) {
            int counterEnc = 0;
            for (int j = 0; j < enclosureArr[i].amountAnimals; j++) {
                if (enclosureArr[i].animalsArr[j].group.equalsIgnoreCase(group)) {
                    counterEnc++;
                    counterOvr++;
                }
            }
            System.out.println("\t" + enclosureArr[i] + ": " + counterEnc);
        }
        System.out.println(group + " animals overall: " + counterOvr + "\n");
    }

    // count species
    public void countSpecies() {
        int counter = 0;
        String[] speciesArr = new String[100];
        // Loop enclosures
        for (int i = 0; i < amountEnclosures; i++) {
            // Loop animals
            for (int j = 0; j < enclosureArr[i].amountAnimals; j++) {
                // get species
                String species = enclosureArr[i].animalsArr[j].species;
                //check if species already in array
                for (int k = 0; k < speciesArr.length; k++) {
                    // set first null to species
                    if (speciesArr[0] == null) {
                        speciesArr[k] = species;
                        counter++;
                        break;
                    }
                    if (species.equalsIgnoreCase(speciesArr[k])) {
                        break;
                    }
                    if (speciesArr[k] == null) {
                        speciesArr[k] = species;
                        counter++;
                        break;
                    }
                }
            }
        }
        System.out.println(counter);
    }

    // getter/setter
    public Person getHead() {
        return head;
    }

    public void setHead(Person head) {
        this.head = head;
    }
}
