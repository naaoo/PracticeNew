package com.company;

public class Main {

    // Es wäre sicher besser gewesen stärker in den Objekten zu arbeiten im Moment sind Objekte eher thematisch zusammengefasste Funktionssammlungen
    // (Customer Parameter wie location, id usw. könnten im Bestellprozess im Programm angelegt werden,
    // selbes bei Orders (alle Infos könnten in Arrays liegen uns müssten so später nicht nochmals abgefragt werden...)
    // zu spät gecheckt...
    // einige Funktionen werden eigenartig aufgerufen (siehe unten Order.makeOrder(customerId)

    public static void main(String[] args) {

        // LogIn: checks if customer already in database and asks for password
        // if not known: completes sign up process
        int customerId = Customer.logIn();
        // make Order: everything from choosing dishes to customizing and saving into database, finishes with calculating costs
        Order.makeOrder(customerId);
        // prints receipt for customers' latest order
        Receipt.printReceipt(customerId);
    }
}
