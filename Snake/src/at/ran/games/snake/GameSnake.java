package at.ran.games.snake;

import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class GameSnake extends BasicGame {
    // Umständlich: viele Methoden/Felder die public static sein müssen um Sie von überall aufrufen zu können, hier würde es sicher besser strukturierte Lösungen geben...
    // BlockType war eigentlich unnötig, wird nie wirklich genutzt

    public static int fieldX = 800;
    public static int fieldY = 800;
    public static int frame = 50;
    private int speed = 100;
    public static int amountFood = 25;
    private int deltaSum = 0;
    public static int score = 0;
    Block head = new Block(BlockType.HEAD, 0, 0);
    Snake snake = new Snake(head);
    static MoveDir moveDir = MoveDir.DOWN;
    //static boolean keypressed = false;

    public GameSnake(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Food.placeFood(snake);
        snake.head.x = 100;
        snake.head.y = 100;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        if (deltaSum < speed) {
            deltaSum = deltaSum + delta;
        } else {
            deltaSum = 0;
            snake.moveSnake();
            snake.checkIfCollision();
            snake.checkForFood(snake);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Score
        graphics.drawString("Score: " + score, fieldX - frame, 20);
        // Border
        graphics.drawRect(frame, frame, fieldX, fieldY);
        // Snake
        snake.drawSnake(graphics);
        // Food
        Food.drawFood(graphics);
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new GameSnake("Snake"));
            container.setDisplayMode(fieldX + 2 * frame, fieldY + 2 * frame, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    // Todo: can switch 180° if two cursors are pressed before deltaSum = 50, maybe fix, how?
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP: {
                if (moveDir == moveDir.DOWN) {
                    break;
                } else {
                    this.moveDir = MoveDir.UP;
                    break;
                }
            }
            case Input.KEY_DOWN: {

                if (moveDir == moveDir.UP) {
                    break;
                } else {
                    this.moveDir = MoveDir.DOWN;
                    break;
                }
            }
            case Input.KEY_LEFT: {
                if (moveDir == moveDir.RIGHT) {
                    break;
                } else {
                    this.moveDir = MoveDir.LEFT;
                    break;
                }
            }
            case Input.KEY_RIGHT: {
                if (moveDir == moveDir.LEFT) {
                    break;
                } else {
                    this.moveDir = MoveDir.RIGHT;
                    break;
                }
            }
        }

    }
}
