package com.company;

public class Main {

    public static void main(String[] args) {

	CreditCard creditCard = new CreditCard(200.65);
	ATMCard atmCard = new ATMCard(400.21);
	Cash cash = new Cash(50.17);
	OnAccount onAccount = new OnAccount(false);
	Customer customer = new Customer(creditCard, atmCard, cash, onAccount);

	customer.makePayment();
    }
}
