package com.company;

import java.util.Scanner;

public class Customer {
    CreditCard creditCard;
    ATMCard atmCard;
    Cash cash;
    OnAccount onAccount;

    public Customer(CreditCard creditCard, ATMCard atmCard, Cash cash, OnAccount onAccount) {
        this.creditCard = creditCard;
        this.atmCard = atmCard;
        this.cash = cash;
        this.onAccount = onAccount;
    }

    public boolean pay(IPayment payment, double sum) {
        boolean paid = payment.pay(sum);
        return paid;
    }

    public void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much do you have to pay?");
        double sum = scanner.nextDouble();
        boolean paid = false;
        while (!paid) {
            System.out.println("Which payment method do you want to use?\n1: Credit Card\n2: ATM Card\n3: Cash\n4: On Account\n");
            int method = scanner.nextInt();
            switch (method) {
                case 1 : paid = pay(creditCard, sum); break;
                case 2 : paid = pay(atmCard, sum); break;
                case 3 : paid = pay(cash, sum); break;
                case 4 : paid = pay(onAccount, sum); break;
                default : System.out.println("No known payment method..."); break;
            }
        }
    }
}


