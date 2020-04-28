package com.company.controller;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;
import com.company.view.View;

import java.util.Scanner;

public class DishController {

    public static Dish customizeDish(Dish dish) {
        Scanner intScanner = new Scanner(System.in);
        int mode = 0;
        while (mode != 3) {
            System.out.println("1: add ingredient\n2: subtract ingredient\n3: finished");
            mode = intScanner.nextInt();
            switch (mode) {
                case 1 : // add
                    System.out.println("Please enter the ingredients' number below:");
                    View.displayAllIngredients();
                    int ingredientAddPos = intScanner.nextInt() - 1;
                    Ingredient ingredientAdd = null;
                    try {
                        ingredientAdd = Main.controller.ingredientRepository.ingredientArr.get(ingredientAddPos).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    dish.ingredientsArr.add(ingredientAdd);
                    dish.additionsArr.add(ingredientAdd);
                    dish.price = Math.round((dish.price + ingredientAdd.price) * 100.0) / 100.0;
                    break;
                case 2 : // sub
                    System.out.println("Please enter the ingredients' number below:");
                    View.displayIngredients(dish);
                    int ingredientSubPos = intScanner.nextInt() - 1;
                    Ingredient ingredientSub = null;
                    try {
                        ingredientSub = dish.ingredientsArr.get(ingredientSubPos).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    dish.ingredientsArr.remove(ingredientSubPos);
                    dish.subtractionsArr.add(ingredientSub);
                    break;
            }
        }
        return dish;
    }
}
