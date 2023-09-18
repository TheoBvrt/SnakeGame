package ch.bvrt.snakegame;

public class Child {
    int posY;
    int posX;
    int [][] gameTab;

    public Child (int posY, int posX, int[][] gameTab) {
        this.posY = posY;
        this.posX = posX;
        this.gameTab = gameTab;
    }

    public void childFollow(Child parent) {
        gameTab[posY][posX] = 0;
        posX = parent.posX;
        posY = parent.posY;
    }
}
