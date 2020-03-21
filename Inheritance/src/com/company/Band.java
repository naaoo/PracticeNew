package com.company;

public class Band {
    String name;
    MusicStyle musicStyle;

    int musiciansInBand = 0;
    Musician[] bandMembers = new Musician[10];

    public Band(String name, MusicStyle musicStyle) {
        this.name = name;
        this.musicStyle = musicStyle;
    }

    public void playConcert() {
        // check if band has Members
        if (musiciansInBand < 1) {
            // no members
            System.out.println(this.name + " doesn't have any members");
        } else {
            // performance:
            System.out.println("\n" + this.name + " are performing:");
            for (Musician musician : bandMembers) {
                if (musician != null) {
                    System.out.println(musician.name + " is making " + this.musicStyle + " " + musician.makeMusic());
                }
            }
            for (Musician musician : bandMembers) {
                if (musician != null) {
                    System.out.println(musician.sayThankYou());
                }
            }
            System.out.println(this.name + " are leaving the stage\n");
        }
    }
}
