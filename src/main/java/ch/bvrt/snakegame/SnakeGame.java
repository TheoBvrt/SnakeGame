package ch.bvrt.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame {
    private final int spriteSize;
    private final int tabSize;
    private final GraphicsContext gc;
    int score = 2;

    public SnakeGame (int gameSpriteSize, int gameTabSize, GraphicsContext graphicsContext) {
        spriteSize = gameSpriteSize;
        tabSize = gameTabSize / 10;
        gc = graphicsContext;
    }
    public void Run () {
        int[][] gameTab = TabFill();
        ParentNode parent = new ParentNode(20, 20);

        GameLoop(gameTab, parent);
        System.out.println("Welcome to SnakeGame !");
        System.out.println(tabSize);
    }

    private void GameLoop(int [][] gameTab, ParentNode parent) {
        List<Node> nodes = new ArrayList<>();


        Node child = new Node(gameTab);
        Node child2 = new Node(gameTab);
        Node child3 = new Node(gameTab);
        Node child4 = new Node(gameTab);

        nodes.add(child);
        nodes.add(child2);
        nodes.add(child3);
        nodes.add(child4);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            parent.lastX = parent.posX;
            parent.lastY = parent.posY;
            parent.posY = Movement.Forward(gameTab, parent.posY, parent.posX);
            parent.lastDir = 1;
            moveChild(nodes, parent);
            frameUpdate(gameTab);
        }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            parent.lastX = parent.posX;
            parent.lastY = parent.posY;
            parent.posX = Movement.Right(gameTab, parent.posY, parent.posX);
            parent.lastDir = 2;
            moveChild(nodes, parent);
            frameUpdate(gameTab);
        }
    }

    private void moveChild(List<Node> childList, ParentNode parent) {
        for (int i = 0; i < childList.size(); i++) {
            if (i == 0) {
                childList.get(i).parentFollow(parent);
            } else {
                childList.get(i).childFollow(childList.get(i - 1));
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
