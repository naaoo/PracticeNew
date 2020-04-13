package com.company;

public class Main {

    // Es wäre sicher besser gewesen stärker in den Objekten zu arbeiten (Customer Parameter werden im Bestellprozess im Programm angelegt, selbes bei Orders...)
    // zu spät realisiert...
    // einige Funktionen werden eigenartig aufgerufen (siehe unten Order.makeOrder(customerId)

    static int customerId;

    public static void main(String[] args) {

        customerId = Customer.logIn();
        Order.makeOrder(customerId);

    }
}
