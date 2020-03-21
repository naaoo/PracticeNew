package com.company;

public class CreditCard implements IPayment{
    double availableBalance;

    public CreditCard(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public boolean pay(double sum) {
        boolean paid = false;
        sum = sum * 1.03;
        System.out.println("3% fees are added. Sum to pay: " + sum + "€");
        if (sum > this.availableBalance) {
            System.out.println("I'm afraid you don't have enough balance on your credit card");
        } else {
            System.out.println("Sum " + sum + "€ has been paid with credit Card");
            this.availableBalance =- sum;
            paid = true;
        }
        return paid;
    }
}
