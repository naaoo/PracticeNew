package com.company;

public class Artist extends Person {

    Art art;

    int amountArtists = 0;
    Artist[] artists = new Artist[100];

    public Artist(String name, int age) {
        super(name, age);
        artists[amountArtists] = this;
        amountArtists++;
    }

    public String makeArt() {
        return "Art is produced";
    }
}
