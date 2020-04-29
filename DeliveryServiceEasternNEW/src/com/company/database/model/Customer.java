package com.company.database.model;

public class Customer {
    public Integer id;
    public String firstName;
    public String lastName;
    public Location location;
    public String password;
    public Integer amountPastOrders;
    public double discountRate;
    public boolean isBlocked;

    public Customer(int id, String firstName, String lastName, String locationName, double locationPrice,
                    String password, int amountPastOrders, double discountRate, boolean isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = new Location(locationName, locationPrice);
        this.password = password;
        this.amountPastOrders = amountPastOrders;
        this.discountRate = discountRate;
        this.isBlocked = isBlocked;
    }
}
