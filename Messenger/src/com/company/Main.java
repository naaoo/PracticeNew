package com.company;

public class Main {

    public static void main(String[] args) {
        WhatsApp whatsApp = new WhatsApp();
        Slack slack = new Slack();
        Person andi = new Person();

        whatsApp.receiveMessage("Hallo Welt");
        slack.sendMessage("I'm Slack");
        andi.receiveMessage(slack, "Hallo Andi!");
        andi.sendMessage(slack, "Hallo Slack!");

    }
}
