package at.ran.games.snake;

import org.newdawn.slick.Color;

import java.awt.*;
import java.util.Random;

public class Food extends Block{
    public static Block[] foodArr = new Block[GameSnake.amountFood];

    public Food(BlockType blockType, int x, int y, int positionArr) {
        super(blockType, x , y);
        foodArr[positionArr] = this;
    }

    public static void drawFood(org.newdawn.slick.Graphics graphics) {
        graphics.setColor(Color.red);
        for (int i = 0; i < Food.foodArr.length; i++) {
            graphics.fillRect(foodArr[i].x, foodArr[i].y, foodArr[i].size - 5, foodArr[i].size - 5);
        }
        graphics.setColor(Color.white);
    }

    public static void placeFood(Snake snake) {
        // for i would have worked as well...
        int i = 0;
        for (Block food : foodArr) {
            if (food == null) {
                boolean taken = true;
                while (taken) {
                    int x = getRandom(GameSnake.fieldX);
                    int y = getRandom(GameSnake.fieldY);
                    boolean open = checkIfOpen(snake, x, y);
                    if (open) {
                        new Food(BlockType.FOOD, x, y, i);
                        taken = false;
                    }
                }
            }
            i++;
        }
    }

    // checks if coordinates are taken by body and returns false if taken
    private static boolean checkIfOpen(Snake snake, int x, int y) {
        boolean open = true;
        for (int i = 0; i < snake.bodyParts; i++) {
            if (snake.body[i] != null && snake.body[i].x == x && snake.body[i].y == y) {
                open = false;
                break;
            }
        }
        // leftover?
        /*for (Block bodyPart : snake.body) {
            if (bodyPart != null) {
                if (bodyPart.x == x && bodyPart.y == y) {
                    open = false;
                    break;
                }
            }
        }
         */
        return open;
    }

    // Random number has to be % 25 = 0 since whole playing field is construed in 25/25 (size) grid
    public static int getRandom(int fieldSize) {
        Random random = new Random();
        int randomNumber = random.nextInt(fieldSize) + GameSnake.frame;
        int remainder = randomNumber % Block.size;
        randomNumber = randomNumber - remainder;
        return randomNumber;
    }
}
