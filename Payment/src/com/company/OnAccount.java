package com.company;

public class OnAccount implements IPayment{
    boolean trustworthiness;

    public OnAccount(boolean trustworthiness) {
        this.trustworthiness = trustworthiness;
    }

    @Override
    public boolean pay(double sum) {
        if (this.trustworthiness == true) {
            System.out.println("Sum " + sum + "€ has to be paid within 14 days to this and that IBAN bla...");
        } else {
            System.out.println("Sorry, we can't do that... Better get out of our shop...");
            System.exit(0);
        }
        return true;
    }
}
