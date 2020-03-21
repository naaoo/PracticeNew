package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Camera {
    String brand;
    String exactName;
    int megaPixel;
    String origin;
    Lens lens;
    boolean videoFunct;
    boolean selftimer;
    MemoryCard memoryCard;

    Scanner scanner = new Scanner(System.in);

    public void takePicture(Camera camera) {
        int pictureSize = 5;
        if (camera.getMemoryCard().getFreeSpace() < pictureSize) {
            System.out.println("Not enough space on card left. Please delete some pictures.");
        } else {
            camera.getMemoryCard().setSavedPics(camera.getMemoryCard().getSavedPics() + 1);
            System.out.println("Camera " + camera + ": Klick");
            System.out.println("Memory card in Camera has " + camera.getMemoryCard().getSavedPics() + " saved pictures");
            camera.getMemoryCard().setFreeSpace(camera.getMemoryCard().getFreeSpace() - pictureSize);
            System.out.println("Space on camera " + camera + " left: " + camera.getMemoryCard().getFreeSpace() + "MB (" + camera.getMemoryCard().getFreeSpacePerc() + "%)");
        }
    }

    public void deletePictures(Camera camera) {
        System.out.println("How many pictures shall be deleted?");
        int pictures = scanner.nextInt();
        int pictureSize = 5;
        camera.getMemoryCard().setSavedPics(camera.getMemoryCard().getSavedPics() - pictures);
        camera.getMemoryCard().setFreeSpace(camera.getMemoryCard().getFreeSpace() + pictures * pictureSize);
        System.out.println(pictures + " pictures deleted (" + camera.getMemoryCard().getSavedPics() + " pictures still saved)");
        System.out.println("Space on camera " + camera + " left: " + camera.getMemoryCard().getFreeSpace() + "MB (" + camera.getMemoryCard().getFreeSpacePerc() + "%)");
    }

    @Override
    public String toString() {
        return brand + " " + exactName;
    }

    public static void printProductData(Camera[] allCameras, int i) {
        System.out.println(allCameras[i].getBrand() + " " + allCameras[i].getExactName());
        System.out.println("Origin: " + allCameras[i].getOrigin());
        System.out.println("Megapixel: " + allCameras[i].getMegaPixel());
        System.out.println("Equipped lens: " + allCameras[i].getLens());
        System.out.println("Focal range: " + allCameras[i].getLens().getFocalRange());
        System.out.println("Memory card: " + allCameras[i].getMemoryCard());
        System.out.println("Card size: " + allCameras[i].getMemoryCard().getSize());
        System.out.println("Free space: " + allCameras[i].getMemoryCard().getFreeSpace());
        if (allCameras[i].getSelftimer()) {
            System.out.println("Selftimer");
        }
        if (allCameras[i].getVideoFunct()) {
            System.out.println("Video Function");
        }
        System.out.println();
    }

    public void setMemoryCard(MemoryCard memoryCard) { this.memoryCard = memoryCard; }
    public MemoryCard getMemoryCard() {return memoryCard; }

    public void setLens(Lens lens) {
        this.lens = lens;
    }
    public Lens getLens() { return lens; }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }

    public void setExactName(String exactName) {
        this.exactName = exactName;
    }
    public String getExactName() {
        return exactName;
    }

    public void setMegaPixel(int megaPixel) {
        if (megaPixel < 1) {
            System.out.println("value of megapixel can't be lower than 1");
        }
        else {
            this.megaPixel = megaPixel;
        }
    }
    public int getMegaPixel() {
        return megaPixel;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getOrigin() {
        return origin;
    }

    public void setVideoFunct(boolean videoFunct) {
        this.videoFunct = videoFunct;
    }
    public boolean getVideoFunct() {
        return videoFunct;
    }

    public void setSelftimer(boolean selftimer) {
        this.selftimer = selftimer;
    }
    public boolean getSelftimer() {
        return selftimer;
    }


}
