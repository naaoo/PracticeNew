package com.company;

public class Animal {

    String group;
    String species;
    String gender;
    double weight;
    int age;
    Enclosure enclosure;

    public Animal(Zoo zoo, Enclosure enclosure, String group, String species, String gender, double weigth, int age) {
        if (species.equalsIgnoreCase("lion")) {
            if (!enclosure.topNet || enclosure.barrier != EnclosureTypes.glass) {
                System.out.println(species + " can't be kept in this enclosure. Please assign again and restart.");
                System.exit(0);
            }
        }
        this.enclosure = enclosure;
        this.group = group;
        this.species = species;
        this.gender = gender;
        this.weight = weigth;
        this.age = age;
        enclosure.animalsArr[enclosure.amountAnimals] = this;
        zoo.amountAnimals++;
        enclosure.amountAnimals++;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
