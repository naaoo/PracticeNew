package com.company;

public class Main {

    public static void main(String[] args) {

        /* Methods:
        -) Musicians only know certain styles (setKnownStyle)
        -) can only join band if they know its style (setBand)
        -) can change bands (changeBand) - if they don't know new bands style they still leave their old band
        -) can make music sounds
        -) Bands can play concerts (members make music sound with their instruments in the bands style)
         */

        Guitarist andy = new Guitarist("Andy", 24);
        andy.setKnownStyle(MusicStyle.COUNTRY);
        andy.setKnownStyle(MusicStyle.ROCK);
        Bassist alex = new Bassist("Alex", 25);
        alex.setKnownStyle(MusicStyle.COUNTRY);
        alex.setKnownStyle(MusicStyle.METAL);
        Drummer joanne = new Drummer("Joanne", 26);
        joanne.setKnownStyle(MusicStyle.COUNTRY);
        joanne.setKnownStyle(MusicStyle.HIPHOP);
        Vocalist bill = new Vocalist("Billy", 22);
        bill.setKnownStyle(MusicStyle.COUNTRY);
        bill.setKnownStyle(MusicStyle.ROCK);

        Band billy = new Band("Billy and the Hillys", MusicStyle.COUNTRY);
        Band cov2 = new Band("CoV 2", MusicStyle.METAL);
        Band thunder = new Band("Thunderstorm", MusicStyle.ROCK);
        Band hop = new Band("Hoppers", MusicStyle.HIPHOP);

        andy.setBand(billy);
        alex.setBand(billy);
        joanne.setBand(billy);
        bill.setBand(billy);

        billy.playConcert();

        alex.changeBand(cov2);
        alex.changeBand(billy);

        cov2.playConcert();

        billy.playConcert();


    }
}
