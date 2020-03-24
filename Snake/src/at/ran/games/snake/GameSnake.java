package at.ran.games.snake;

import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class GameSnake extends BasicGame {
    // a lot of parameters are public static to be accessible from everywhere, better structured solutions possible...?
    // BlockType was unnecessary, never really used

    public static int fieldX = 700;
    public static int fieldY = 700;
    public static int frame = 50;
    private int speed;
    public static int amountFood = 25;
    private int deltaSum = 0;
    public static int score = 0;
    public static int highScore = 0;
    Block head = new Block(BlockType.HEAD, 0, 0);
    Snake snake = new Snake(head);
    static MoveDir moveDir = MoveDir.DOWN;
    public static GameState gameState = GameState.START;
    public static GameMode gameMode;
    int pressed = 0;
    MoveDir nextDir;

    public GameSnake(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Food.placeFood(snake);
        snake.head.x = frame + 50;
        snake.head.y = frame + 50;
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
            if (pressed == 2) {
                moveDir = nextDir;
                pressed--;
            } else {
                pressed = 0;
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        switch (this.gameState) {
            case START :
                graphics.drawString("     Welcome to Snake!\n\n\nPlease choose your game mode:", frame + fieldX / 2 - 135, frame + fieldY / 2 - 130);
                graphics.drawString("1: Beginner\n2: Advanced\n3: Expert (Border = death)", frame + fieldX / 2 - 135, frame + fieldY / 2 - 10);
                break;
            case MODE_CHOSEN :
                graphics.drawString("Press Space to start the game", fieldX / 2 - 80, fieldY / 2);
                break;
            case RUNNING :
                // Score
                graphics.drawString("Difficulty: " + this .gameMode + "        Session Highscore: " + highScore + "         Score: " + score, fieldX - frame - 500, 20);
                // Border
                graphics.drawRect(frame, frame, fieldX, fieldY);
                // Snake
                snake.drawSnake(graphics);
                // Food
                Food.drawFood(graphics);
                break;
            case GAME_OVER :
                graphics.drawString("          Game over\n\n\nScore: " + score + " (Session highscore: " + highScore + ")\n\n-) Play again? (press y / n)\n\n-) Back to menu (press m)", fieldX / 2 - 75, fieldY / 2 - 70);
                break;
        }
    }

    private void chooseMode(int mode) {
        switch (mode) {
            case 1 :
                this.gameMode = GameMode.BEGINNER;
                this.speed = 150;
                break;
            case 2 :
                this.gameMode = GameMode.ADVANCED;
                this.speed = 100;
                break;
            case 3 :
                this.gameMode = GameMode.EXPERT;
                this.speed = 100;
                break;
        }
        this.gameState = GameState.MODE_CHOSEN;
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

    public void resetGame() {
        snake.body = new Block[10000];
        Food.foodArr = new Block[GameSnake.amountFood];
        Food.placeFood(snake);
        snake.bodyParts = 0;
        // reset position
        snake.head.x = frame + 50;
        snake.head.y = frame + 50;
        // reset direction
        this.moveDir = MoveDir.DOWN;
        this.score = 0;

    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP: {
                if (moveDir == moveDir.DOWN) {
                    break;
                } else if (pressed == 1) {
                    this.nextDir = MoveDir.UP;
                    pressed = 2;
                    break;
                } else {
                    this.moveDir = MoveDir.UP;
                    pressed = 1;
                    break;
                }
            }
            case Input.KEY_DOWN: {
                if (moveDir == moveDir.UP) {
                    break;
                } else if (pressed == 1) {
                    this.nextDir = MoveDir.DOWN;
                    pressed = 2;
                    break;
                } else {
                    this.moveDir = MoveDir.DOWN;
                    pressed = 1;
                    break;
                }
            }
            case Input.KEY_LEFT: {
                if (moveDir == moveDir.RIGHT) {
                    break;
                }
                else if (pressed == 1) {
                    this.nextDir = MoveDir.LEFT;
                    pressed = 2;
                    break;
                } else {
                    this.moveDir = MoveDir.LEFT;
                    pressed = 1;
                    break;
                }
            }
            case Input.KEY_RIGHT: {
                if (moveDir == moveDir.LEFT) {
                    break;
                }
                else if (pressed == 1) {
                    this.nextDir = MoveDir.RIGHT;
                    pressed = 2;
                    break;
                } else {
                    this.moveDir = MoveDir.RIGHT;
                    pressed = 1;
                    break;
                }
            }
            case Input.KEY_1:
            case Input.KEY_NUMPAD1: {
                chooseMode(1);
                break;
            }
            case Input.KEY_2:
            case Input.KEY_NUMPAD2: {
                chooseMode(2);
                break;
            }
            case Input.KEY_3:
            case Input.KEY_NUMPAD3: {
                chooseMode(3);
                break;
            }
            case Input.KEY_SPACE: {
                if (this.gameState == GameState.MODE_CHOSEN) {
                    this.gameState = GameState.RUNNING;
                }
                break;
            }
            case Input.KEY_Y: {
                if (this.gameState == GameState.GAME_OVER) {
                    this.gameState = GameState.MODE_CHOSEN;
                    resetGame();
                }
                break;
            }
            case Input.KEY_N: {
                if (this.gameState == GameState.GAME_OVER) {
                    this.gameState = GameState.START;
                    resetGame();
                    System.exit(0);
                }
                break;
            }
            case Input.KEY_M: {
                if (this.gameState == GameState.GAME_OVER) {
                    this.gameState = GameState.START;
                    resetGame();
                    GameSnake.highScore = 0;
                }
                break;
            }
        }
    }
}
