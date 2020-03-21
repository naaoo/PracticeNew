package com.company;

import java.util.Scanner;

public class DeliveryService {
    String name;
    Dish[] availableDishes = new Dish[10];
    int amountDishes = 0;
    CustomPizza[] customPizzas = new CustomPizza[20];

    public DeliveryService(String name) {
        this.name = name;
    }

    public void addDish(Dish dish) {
        this.availableDishes[this.amountDishes] = dish;
        this.amountDishes++;
    }

    public void printAvailableDishes() {
        System.out.println("\nWelcome to " + this.name + "! These are our pizza options:");
        for (int i = 0; i < this.amountDishes; i++) {
            System.out.println(i + "\t" + this.availableDishes[i]);
        }
    }

    public void getOrder() {
        Scanner scanner = new Scanner(System.in);
        int end = 0;
        int pizzasOrdered = 0;

        while (end == 0) {
            this.customPizzas[pizzasOrdered] = this.choosePizza();
            pizzasOrdered++;
            System.out.println("Do you want to order another pizza? (yes / no)");
            String endString = scanner.nextLine();
            if (endString.substring(0, 1).equalsIgnoreCase("n")) {
                end = 1;
            }
        }
        printReceipt();

    }

    public CustomPizza choosePizza() {
        Ingredient[] choiceIngredients = new Ingredient[20];
        Scanner scanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        this.printAvailableDishes();

        System.out.println("Please choose your pizza: (enter number)");
        int choice = scanner.nextInt();
        double pricePizza = this.availableDishes[choice].price;

        // fill choiceIngredients with default ingredients
        for (int i = 0; i < this.availableDishes[choice].ingredients.length; i++) {
            choiceIngredients[i] = this.availableDishes[choice].ingredients[i];
        }

        //print default ingredients
        System.out.println(this.availableDishes[choice].name + " contains:");
        for (int i = 1; i < this.availableDishes[choice].ingredients.length; i++) {
            if (i == this.availableDishes[choice].ingredients.length - 1) {
                System.out.println(this.availableDishes[choice].ingredients[i].name);
            } else {
                System.out.print(this.availableDishes[choice].ingredients[i].name + ", ");
            }
        }
        this.subIngredients(choiceIngredients);
        pricePizza = this.addIngredients(choiceIngredients, choice, pricePizza);
        CustomPizza custom = new CustomPizza(this.availableDishes[choice].name, pricePizza, choiceIngredients);
        return custom;
    }

        // sub ingredients
    public void subIngredients(Ingredient[] choiceIngredients) {
        while (true) {
            Scanner stringScanner = new Scanner(System.in);
            System.out.println("Do you want to take any ingredient away?\nPlease type in the ingredient or 'no'");
            String subIngredient = stringScanner.nextLine();
            if (subIngredient.equalsIgnoreCase("no")) {
                return;
            } else {
                for (int i = 0; i < choiceIngredients.length; i++) {
                    if (choiceIngredients[i] == null) {
                        continue;
                    }
                    if (choiceIngredients[i].ingridientString.equalsIgnoreCase(subIngredient)) {
                        choiceIngredients[i] = null;
                    }
                }
            }
            System.out.println("subtrated " + subIngredient);
            System.out.print("Your Pizza: ");
            for (Ingredient ingredient : choiceIngredients) {
                if (ingredient != null) {
                    System.out.print(ingredient + ", ");
                }
            }
            System.out.println();
        }
    }

    public double addIngredients(Ingredient[] choiceIngredients, int choice, double pricePizza) {
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("\nDo you want to add one of the following ingredients?");

        for (int i = 0; i < Ingredient.allIngredients.length; i++) {
            if (Ingredient.allIngredients[i] == null) {
                break;
            }
            System.out.println(i + "  " + Ingredient.allIngredients[i].name + "  (" + Ingredient.allIngredients[i].price + "€)");
        }

        while (true) {
            System.out.println("Please enter a number or 'no':");
            String add = stringScanner.nextLine();
            if (add.substring(0, 1).equalsIgnoreCase("n")) {
                System.out.println("Your pizza will come with the following ingredients:");
                for (int i = 1; i < choiceIngredients.length; i++) {
                    if (choiceIngredients[i] == null) {
                        continue;
                    }
                    System.out.print(choiceIngredients[i].name + ", ");
                }
                System.out.println("Price: " + Math.round(pricePizza * 100.0) / 100.0 + "€");
                break;
            } else {
                int addInt = Integer.parseInt(add);
                for (int i = this.availableDishes[choice].amountIngredients - 1; i < choiceIngredients.length; i++) {
                    if (choiceIngredients[i] == null) {
                        choiceIngredients[i] = Ingredient.allIngredients[addInt];
                        // add price
                        pricePizza = pricePizza + Ingredient.allIngredients[addInt].price;
                        // Output
                        System.out.println("added " + Ingredient.allIngredients[addInt]);
                        System.out.print("Your Pizza: ");
                        for (Ingredient ingredient : choiceIngredients) {
                            if (ingredient != null) {
                                System.out.print(ingredient + ", ");
                            }
                        }
                        System.out.println("Price: " + Math.round(pricePizza * 100.0) / 100.0 + "€");
                        break;
                    }
                }
            }
            System.out.println("Do you want to add another ingredient?");
        }
        System.out.println();
        return pricePizza;
    }

    public void printReceipt() {
        double priceOvr = 0;
        System.out.println("Your complete order:");
        for (CustomPizza pizza : customPizzas) {
            if (pizza != null) {
                priceOvr = priceOvr + pizza.price;
                System.out.println(pizza.name + " (" + pizza.price + "€)");
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("Price overall: " + priceOvr + "€");
    }






    @Override
    public String toString() {
        return name;
    }
}
