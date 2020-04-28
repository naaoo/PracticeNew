package com.company.database.repositories;

import com.company.Main;
import com.company.database.model.Dish;
import com.company.database.model.Ingredient;
import com.company.database.model.Order;

public class OrderSender {

    public static void updateDatabase(Order order) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        // order
        String insertOrder = "INSERT into orders (customer_id, costs_pure, costs_after_discount, " +
                "delivery_costs, costs_overall)" +
                "VALUES (" + Main.controller.customer.id + ", " + order.costsPure + ", " +
                order.costsAfterDiscount + ", " + order.deliveryCosts + ", " + order.costsOverall + "); ";
        dbConnector.insert(insertOrder);
        // customer pastOrders
        String updatePastOrders = "UPDATE customers SET past_orders = past_orders + 1 WHERE customers.id = "
                + Main.controller.customer.id + "; ";
        dbConnector.update(updatePastOrders);
        // order details (for each dish)
        for (Dish dish : order.dishes) {
            String insertOrderDetails = "INSERT INTO order_details (order_id, dish_id, price) " +
                    "VALUES ((SELECT MAX(id) FROM orders), " + dish.id + ", " + dish.price + "); ";
            dbConnector.insert(insertOrderDetails);
            // order changes (for each adds + subs)
            for (Ingredient ingredient : dish.additionsArr) {
                String insertOrderChangesAdd = "INSERT INTO order_changes (order_details_id, kind, ingredient_id) " +
                        "VALUES ((SELECT MAX(id) FROM order_details), 'add', " + ingredient.id + "); ";
                dbConnector.insert(insertOrderChangesAdd);
            }
            for (Ingredient ingredient : dish.subtractionsArr) {
                String insertOrderChangesSub = "INSERT INTO order_changes (order_details_id, kind, ingredient_id) " +
                        "VALUES ((SELECT MAX(id) FROM order_details), 'sub', " + ingredient.id + "); ";
                dbConnector.insert(insertOrderChangesSub);
            }
        }
    }
}
