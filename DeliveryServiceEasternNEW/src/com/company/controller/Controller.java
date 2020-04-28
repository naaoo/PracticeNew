package com.company.controller;

import com.company.database.model.Customer;
import com.company.database.model.Order;
import com.company.database.repositories.DishRepository;
import com.company.database.repositories.IngredientRepository;
import com.company.database.repositories.OrderSender;
import com.company.view.View;

// is kind of a main controller (brings other controllers together)
// isn't really necessary, could also just have been in Main...

public class Controller {
    public Customer customer;
    public IngredientRepository ingredientRepository = new IngredientRepository();
    public DishRepository dishRepository = new DishRepository();
    public Order order;

    public void startCustomerSystem() {
        ingredientRepository.findAll();
        dishRepository.findAll();
        customer = CustomerController.logIn();
        order = OrderController.order();
        View.printReceipt(order);
        OrderSender.updateDatabase(order);
    }
}
