package ch.bvrt.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame {
    private final int spriteSize;
    private final int tabSize;
    private final GraphicsContext gc;
    int playerY = 20;
    int playerX = 20;
    int score = 2;

    public SnakeGame (int gameSpriteSize, int gameTabSize, GraphicsContext graphicsContext) {
        spriteSize = gameSpriteSize;
        tabSize = gameTabSize / 10;
        gc = graphicsContext;
    }
    public void Run () {
        int[][] gameTab = TabFill();
        Child parent = new Child(playerY, playerX, gameTab);

        GameLoop(gameTab, parent);
        System.out.println("Welcome to SnakeGame !");
        System.out.println(tabSize);
    }

    private void GameLoop(int [][] gameTab, Child parent) {
        List<Child> list = new ArrayList<>();
        score = 1;

        Child child = new Child(playerY, playerX, gameTab);
        list.add(child);

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            parent.posY = Movement.Left(gameTab, parent.posY, parent.posX);
            frameUpdate(gameTab);
        }
    }

    private void moveChild(int[][] gameTab, List<Child> childList, Child parent) {
        for (int i = 0; i < childList.size(); i++) {
            if (i == 1) {
                childList.get(0).childFollow(parent);
            }
        }
    }

    private boolean gameIsOver(int[][] gameTab) {
        return (false);
    }


    private int[][] TabFill () {
        int [][] tabToReturn = new int[tabSize][tabSize];
        for (int i = 0; i < tabSize; i++) {
            for (int j = 0; j < tabSize; j++) {
                tabToReturn[i][j] = 0;
            }
        }
        return  tabToReturn;
    }

    private void frameUpdate(int[][] gameTab) {
        int frameY = 0;
        int frameX = 0;

        gc.setFill(Color.RED);
        for (int[] ints : gameTab) {
            for (int gameTabY = 0; gameTabY < gameTab.length; gameTabY++) {
                if (ints[gameTabY] == 0) {
                    DrawCell(frameY, frameX, Color.BLACK);
                } else if (ints[gameTabY] == 1){
                    DrawCell(frameY, frameX, Color.RED);
                }
                frameX += spriteSize;
            }
            frameY += spriteSize;
            frameX = 0;
        }
    }

    private void DrawCell(int frameY, int frameX, Color color) {
        for (int y = 0; y < spriteSize; y++) {
            for (int x = 0; x < spriteSize; x++) {
                gc.setFill(color);
                gc.fillRect(x + frameX, y + frameY, 1, 1);
            }
        }
    }
}
