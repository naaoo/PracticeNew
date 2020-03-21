package com.company;

public class Main {

    public static void main(String[] args) {

        MemoryCard[] allMemoryCards = new MemoryCard[2];

        MemoryCard memoryCard0 = new MemoryCard();
        allMemoryCards[0] = memoryCard0;
        memoryCard0.setBrand("Kingston");
        memoryCard0.setName("E62/100");
        memoryCard0.setSize(100);
        memoryCard0.setFreeSpace(memoryCard0.getSize());

        MemoryCard memoryCard1 = new MemoryCard();
        allMemoryCards[0] = memoryCard1;
        memoryCard1.setBrand("SanDisk");
        memoryCard1.setName("Transcend SD 200");
        memoryCard1.setSize(200);
        memoryCard1.setFreeSpace(memoryCard1.getSize());

        Lens[] allLenses = new Lens[2];

        Lens lens0 = new Lens();
        allLenses[0] = lens0;
        lens0.setBrand("Nikon");
        lens0.setName("OX 2000");
        lens0.setFocalLength(10, 100);

        Lens lens1 = new Lens();
        allLenses[1] = lens1;
        lens1.setBrand("Leica");
        lens1.setName("Superlens 3");
        lens1.setFocalLength(20, 60);

        Camera[] allCameras = new Camera[3];

        Camera camera0 = new Camera();
        allCameras[0] = camera0;
        camera0.setBrand("Nikon");
        camera0.setExactName("XP 1000");
        camera0.setLens(lens0);
        camera0.setMegaPixel(7);
        camera0.setOrigin("Japan");
        camera0.setSelftimer(true);
        camera0.setVideoFunct(false);
        camera0.setMemoryCard(memoryCard0);

        Camera camera1 = new Camera();
        allCameras[1] = camera1;
        camera1.setBrand("Sony");
        camera1.setExactName("6000K");
        camera1.setLens(lens0);
        camera1.setMegaPixel(10);
        camera1.setOrigin("Japan");
        camera1.setSelftimer(true);
        camera1.setVideoFunct(true);
        camera1.setMemoryCard(memoryCard1);

        Camera camera2 = new Camera();
        allCameras[2] = camera2;
        camera2.setBrand("Canon");
        camera2.setExactName("Supermaster 8700");
        camera2.setLens(lens1);
        camera2.setMegaPixel(20);
        camera2.setOrigin("Japan");
        camera2.setSelftimer(true);
        camera2.setVideoFunct(true);
        camera2.setMemoryCard(memoryCard1);

        for (int i = 0; i < allCameras.length; i++) {
            Camera.printProductData(allCameras, i);
        }

        camera0.takePicture(camera0);
        camera0.setMemoryCard(memoryCard1);
        for (int i = 0; i < 41; i++) {
            camera0.takePicture(camera0);
        }
        camera0.deletePictures(camera0);
    }
}
