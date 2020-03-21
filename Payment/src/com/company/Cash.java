package com.company;

public class Cash implements IPayment{
    double availableBalance;

    public Cash(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public boolean pay(double sum) {
        boolean paid = false;
        if (sum > this.availableBalance) {
            System.out.println("I'm afraid you dont have enough cash to pay");
        } else {
            System.out.println("Sum " + sum + "€ has been paid cash");
            this.availableBalance =- sum;
            paid = true;
        }
        return paid;
    }
}
