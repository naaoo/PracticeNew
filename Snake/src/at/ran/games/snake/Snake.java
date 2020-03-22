package at.ran.games.snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import java.util.Arrays;

public class Snake extends Block{
    Block head;
    Block[] body = new Block[10000];
    public static int bodyParts = 0; //0
    public static int size = 25;

    public Snake(Block head) {
        super(BlockType.HEAD, 0, 0);
        this.head = head;
    }

    public void drawSnake(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRoundRect(this.head.x, this.head.y, size,size, 10);
        graphics.setColor(Color.white);
        drawBody(graphics);
    }

    private void drawBody(Graphics graphics) {
        graphics.setColor(Color.yellow);
        for (Block body : body) {
            if (body != null) {
                graphics.fillRoundRect(body.x, body.y, size,size, 10);
            }
        }
        graphics.setColor(Color.white);
    }

    // check if out of bounds and set back
    public void checkSetBack() {
        if (this.head.x > GameSnake.fieldX + GameSnake.frame - this.size) {
            this.head.x = GameSnake.frame;
        }
        if (this.head.x < GameSnake.frame) {
            this.head.x = GameSnake.fieldX + GameSnake.frame - this.size;
        }
        if (this.head.y > GameSnake.fieldY + GameSnake.frame - this.size) {
            this.head.y = GameSnake.frame;
        }
        if (this.head.y < GameSnake.frame) {
            this.head.y = GameSnake.fieldY + GameSnake.frame - this.size;
        }
    }

    public void moveHead() {
        switch (GameSnake.moveDir) {
            case UP:
                this.head.y -= size;
                break;
            case DOWN:
                this.head.y += size;
                break;
            case LEFT:
                this.head.x -= size;
                break;
            case RIGHT:
                this.head.x += size;
                break;
        }
        this.checkSetBack();
    }

    public void checkForFood(Snake snake) {
        for (Block food : Food.foodArr) {
            if (food != null) {
                if (this.head.x == food.x && this.head.y == food.y) {
                    GameSnake.score++;
                    addBodyPart(food);
                    deleteFood(food);
                    Food.placeFood(snake);
                }
            }
        }
    }

    private void addBodyPart(Block bodyPart) {
        this.body[bodyParts] = bodyPart;
        this.bodyParts++;
    }

    private void deleteFood(Block food) {
        for (int i = 0; i < Food.foodArr.length; i++) {
            if (Food.foodArr[i] == food) {
                Food.foodArr[i] = null;
            }
        }
    }

    public void moveSnake() {
        for (int i = this.bodyParts - 1; i > -1; i--) {
            if (this.body[i] != null) {
                if (i == 0) {
                    this.body[i].x = this.head.x;
                    this.body[i].y = this.head.y;
                } else {
                    this.body[i].x = this.body[i - 1].x;
                    this.body[i].y = this.body[i - 1].y;
                }
            }
        }
        moveHead();
    }

    //Todo: implement game modes
    public void checkIfCollision() {
        for (int i = 0; i < this.bodyParts; i++) {
            if (this.body[i].x == this.head.x && this.body[i].y == this.head.y) {
                System.exit(0); // Todo: Implement Game Over
            }
        }
    }

    // more flexible method (no fixed body[size])
    private void addBodyPart1(Block bodyPart) {
        Block[] bodyTemp = Arrays.copyOf(this.body, this.body.length + 1);
        this.body = bodyTemp;
        body[this.bodyParts] = bodyPart;
        this.bodyParts++;
    }

    public void setHead(Block head) {
        this.head = head;
    }
}
