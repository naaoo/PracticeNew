package com.company;

public class Musician extends Artist{

    Instrument instrument;
    Band band;

    MusicStyle[] knownStyles = new MusicStyle[5];
    int amountKnownStyles = 0;

    int amountMusicians = 0;
    Musician[] musicians = new Musician[100];

    public Musician(String name, int age) {
        super(name, age);
        super.art = Art.MUSIC;
        musicians[amountMusicians] = this;
        amountMusicians++;
    }

    public void setKnownStyle(MusicStyle musicStyle) {
        this.knownStyles[amountKnownStyles] = musicStyle;
        this.amountKnownStyles++;
    }

    public String makeMusic() {
        return "music sound";
    }

    public String sayThankYou() {
        return this.name + " is waving to the crowd";
    }

    // Musician joins band
    public void setBand(Band band) {
        for (MusicStyle musicStyle : this.knownStyles) {
            if (musicStyle != null) {
                if (musicStyle == band.musicStyle) {
                    this.band = band;
                    band.bandMembers[band.musiciansInBand] = this;
                    band.musiciansInBand++;
                    System.out.println(this.name + " joined band " + band.name + "");
                    return;
                }
            }
        }
        System.out.println(this.name + " doesn't know the bands style and therefore couldn't join it.");
    }

    public void changeBand(Band band) {
        // delete from old Band
        this.band.musiciansInBand--;
        for (int i = 0; i < this.band.bandMembers.length; i++) {
            if (this.band.bandMembers[i] != null) {
                if (this.band.bandMembers[i] == this) {
                    this.band.bandMembers[i] = null;
                }
            }
        }
        this.band.musiciansInBand--;
        // remaining band members advance in array so there's no nulls between them and 'amountMusicians' points at right position in array (if new member is added)
        for (int i = 0; i < this.band.bandMembers.length - 1; i++) {
            if (this.band.bandMembers[i] == null) {
                this.band.bandMembers[i] = this.band.bandMembers[i + 1];
            }
        }
        // add to new band
        this.setBand(band);
        System.out.println(this.name + " left his/her old band\n");

    }



}
