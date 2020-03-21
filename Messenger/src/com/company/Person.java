package com.company;

public class Person {
    public void receiveMessage(IMessenger messenger, String message) {
        messenger.receiveMessage(message);
    }

    public void sendMessage(IMessenger messenger, String message) {
        messenger.sendMessage(message);
    }
}
