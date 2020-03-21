package com.company;

public class WhatsApp implements IMessenger {

    @Override
    public void receiveMessage(String message) {
        System.out.println(this + " received Message: " + message);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(this + " sent Message: " + message);

    }

    @Override
    public String toString() {
        return "WhatsApp";
    }
}
