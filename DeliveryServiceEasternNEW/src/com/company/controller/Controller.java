package com.company.controller;

import com.company.database.model.Customer;
import com.company.database.model.Order;
import com.company.database.repositories.*;
import com.company.view.View;

import java.util.Scanner;

// is kind of a main controller (brings other controllers together)
// isn't really necessary, could also just have been in Main...

public class Controller {
    public Customer customer;
    public IngredientRepository ingredientRepository = new IngredientRepository();
    public DishRepository dishRepository = new DishRepository();
    public TableRepository tableRepository = new TableRepository();
    public ReservationRepository reservationRepository = new ReservationRepository();
    public Order order;


    public void startCustomerSystem() {
        Scanner intScanner = new Scanner(System.in);
        ingredientRepository.findAll();
        dishRepository.findAll();
        customer = CustomerController.logIn();
        // CustomerController.login returns null if customer is blocked
        if (customer != null) {
            int mode = 0;
            while (mode != 4) {
                System.out.println("Would you like to:\n1: book a table\n2: cancel reservation\n3: order food (delivery)\n4: quit");
                mode = intScanner.nextInt();
                switch (mode) {
                    case 1:
                        ReservationController.makeReservation();
                        break;
                    case 2:
                        ReservationController.cancelReservation();
                        break;
                    case 3:
                        order = OrderController.order();
                        View.printReceipt(order);
                        OrderSender.updateDatabase(order);
                        break;
                    case 4:
                        System.out.println("Thank you and have a nice day!");
                }
            }
        }
    }
}
