package ch.bvrt.snakegame;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

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
        System.out.println(tabSize);
        gameTab[10][25] = 5;
        parent.dir = 1;
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

        Thread thread = new Thread(() -> GameLoop(gameTab, parent));
        thread.start();
    }

    private void GameLoop(int [][] gameTab, ParentNode parent) {
        List<Node> nodes = new ArrayList<>();
        int[] infos;

        Node node = new Node(gameTab);
        Node node1 = new Node(gameTab);
        nodes.add(node);
        nodes.add(node1);

        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (parent.dir == 1) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Forward(gameTab, parent.posY, parent.posX);
                parent.posY = infos[0];

                if (infos[1] == 1) {
                    Node newNode = new Node(gameTab);
                    nodes.add(newNode);
                    FruitGenerator(gameTab);
                }
                moveChild(nodes, parent);
            }

            if (parent.dir == 2) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Right(gameTab, parent.posY, parent.posX);
                parent.posX = infos[0];

                if (infos[1] == 1) {
                    Node newNode = new Node(gameTab);
                    nodes.add(newNode);
                    FruitGenerator(gameTab);
                }
                moveChild(nodes, parent);
            }

            if (parent.dir == 3) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Backward(gameTab, parent.posY, parent.posX);
                parent.posY = infos[0];

                if (infos[1] == 1) {
                    Node newNode = new Node(gameTab);
                    nodes.add(newNode);
                    FruitGenerator(gameTab);
                }
                moveChild(nodes, parent);
            }

            if (parent.dir == 4) {
                parent.lastX = parent.posX;
                parent.lastY = parent.posY;
                infos = Movement.Left(gameTab, parent.posY, parent.posX);
                parent.posX = infos[0];
                if (infos[1] == 1) {
                    Node newNode = new Node(gameTab);
                    nodes.add(newNode);
                    FruitGenerator(gameTab);
                }
                moveChild(nodes, parent);
            }

            System.out.println("Debug");
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

    private void printMatrix(int[][] gameTab) {
        for (int i = 0; i < tabSize; i++) {
            System.out.println(Arrays.toString(gameTab[i]));
        }
    }

    private boolean gameIsOver(int[][] gameTab) {
        return (false);
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
                } else if (ints[gameTabY] == 2) {
                    DrawCell(frameY, frameX, Color.GREEN);
                } else if (ints[gameTabY] == 5) {
                    DrawCell(frameY, frameX, Color.YELLOW);
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
