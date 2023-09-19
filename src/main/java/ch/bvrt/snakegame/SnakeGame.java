package ch.bvrt.snakegame;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SnakeGame {
    private final int spriteSize;
    private final int tabSize;
    private final GraphicsContext gc;
    private final Scene sc;

    public SnakeGame (int gameSpriteSize, int gameTabSize, GraphicsContext graphicsContext, Scene scene) {
        spriteSize = gameSpriteSize;
        tabSize = gameTabSize / 10;
        gc = graphicsContext;
        sc = scene;
    }

    public void Run () {
        int[][] gameTab = TabFill();
        ParentNode parent = new ParentNode(20, 20);

        System.out.println("Welcome to SnakeGame !");
        gameTab[10][25] = 5;
        parent.dir = 1;
        parent.lastDir = parent.dir;
        sc.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch (keyCode) {
                case W :
                    parent.dir = 1;
                    break;
                case D:
                    parent.dir = 2;
                    break;
                case S:
                    parent.dir = 3;
                    break;
                case A:
                    parent.dir = 4;
            }
        });
        FrameUpdater frameUpdater = new FrameUpdater(gameTab, gc, spriteSize);
        frameUpdater.start();
        Thread thread = new Thread(() -> GameLoop(gameTab, parent));
        thread.start();
    }

    private void GameLoop(int [][] gameTab, ParentNode parent) {
        List<Node> nodes = new ArrayList<>();
        int[] infos = new int[0];

        for (int i = 0; i < 3; i++) {
            Node node = new Node(gameTab);
            nodes.add(node);
        }
        while (true) {
            if (parent.dir == 1 && parent.lastDir != 3) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Forward(gameTab, parent.posY, parent.posX);
                parent.lastDir = 1;
                parent.posY = infos[0];
            }
            if (parent.dir == 2 && parent.lastDir != 4) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Right(gameTab, parent.posY, parent.posX);
                parent.lastDir = 2;
                parent.posX = infos[0];
            }
            if (parent.dir == 3 && parent.lastDir != 1) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Backward(gameTab, parent.posY, parent.posX);
                parent.lastDir = 3;
                parent.posY = infos[0];
            }
            if (parent.dir == 4 && parent.lastDir != 2) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Left(gameTab, parent.posY, parent.posX);
                parent.lastDir = 4;
                parent.posX = infos[0];
            }
            if (infos[2] == 1) {
                System.exit(0);
            }
            if (infos[1] == 1) {
                Node newNode = new Node(gameTab);
                nodes.add(newNode);
                FruitGenerator(gameTab);
            }
            moveChild(nodes, parent);
            System.out.println("Last move : " + parent.lastDir);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
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

    private void FruitGenerator(int[][] gameTab) {
        Random rand = new Random();

        int posY = rand.nextInt(tabSize - 1);
        int posX = rand.nextInt(tabSize - 1);
        while (gameTab[posY][posX] != 0) {
            posY = rand.nextInt(tabSize - 1);
            posX = rand.nextInt(tabSize - 1);
        }
        gameTab[posY][posX] = 5;
    }

    private int[][] TabFill () {
        int [][] tabToReturn = new int[tabSize][tabSize];
        for (int i = 0; i < tabSize; i++) {
            for (int j = 0; j < tabSize; j++) {
                if (i == 0 || j == 0 || i == tabSize - 1 || j == tabSize - 1)
                    tabToReturn[i][j] = 10;
                else
                    tabToReturn[i][j] = 0;
            }
        }
        return  tabToReturn;
    }
}
