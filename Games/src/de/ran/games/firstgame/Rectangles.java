package de.ran.games.firstgame;

import org.newdawn.slick.*;
import org.newdawn.slick.tests.AnimationTest;

public class Rectangles extends BasicGame {

    private float x;
    private float y;
    private float speed;

    public Rectangles(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.x = 100;
        this.speed = 50.0f;

    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        this.x += (float)delta/this.speed;
        this.y += (float)delta/this.speed;

        if (this.x > 800 || y > 600) {
            this.x = 0;
            this.y = 0;
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawRect(this.x,this.y,100,100);
        graphics.drawString("Hello Rectangle", 50, 50);
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new Rectangles("Rectangles"));
            container.setDisplayMode(800,600,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
