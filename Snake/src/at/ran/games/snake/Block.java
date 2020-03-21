package at.ran.games.snake;

import java.awt.*;
import java.util.Random;

public class Block {
    BlockType blockType;
    int x;
    int y;
    public static int size = 25;

    public Block(BlockType blockType, int x, int y) {
        this.blockType = blockType;
        this.x = x;
        this.y = y;
    }
}
