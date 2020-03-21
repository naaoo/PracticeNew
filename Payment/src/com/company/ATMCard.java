package com.company;

public class ATMCard implements IPayment{
    double availableBalance;

    public ATMCard(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public boolean pay(double sum) {
        boolean paid = false;
        sum = sum * 0.5;
        System.out.println("Your bank is paying for half of your purchase. Sum to pay: " + sum + "€");
        if (sum > this.availableBalance) {
            System.out.println("I'm afraid you don't have enough balance on your ATM card");
        } else {
            System.out.println("Sum " + sum + "€ has been paid with ATM card");
            this.availableBalance =- sum;
            paid = true;
        }
        return paid;
    }
}
