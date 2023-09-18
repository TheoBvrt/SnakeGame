package ch.bvrt.snakegame;

public class Node {
    int posY = 0;
    int posX = 0;
    int lastY;
    int lastX;
    int [][] gameTab;

    public Node(int[][] gameTab) {
        this.gameTab = gameTab;
    }

    public void parentFollow(ParentNode parent) {
        lastY = posY;
        lastX = posX;
        posX = parent.lastX;
        posY = parent.lastY;
        gameTab[posY][posX] = 1;
        gameTab[lastY][lastX] = 0;
    }

    public void childFollow(Node parent) {
        lastY = posY;
        lastX = posX;
        posX = parent.lastX;
        posY = parent.lastY;
        gameTab[posY][posX] = 1;
        gameTab[lastY][lastX] = 0;
    }
}
