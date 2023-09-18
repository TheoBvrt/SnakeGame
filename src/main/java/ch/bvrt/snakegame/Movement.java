package ch.bvrt.snakegame;

public class Movement {
    public static int Forward (int[][] gameTab, int playerY, int playerX) {
        gameTab[playerY][playerX] = 0;
        gameTab[playerY - 1][playerX] = 1;
        return --playerY;
    }

    public static int Right (int[][] gameTab, int playerY, int playerX) {
        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX + 1] = 1;
        return ++playerX;
    }

    public static int Backward (int[][] gameTab, int playerY, int playerX) {
        gameTab[playerY][playerX] = 0;
        gameTab[playerY + 1][playerX] = 1;
        return ++playerY;
    }

    public static int Left (int[][] gameTab, int playerY, int playerX) {
        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX - 1] = 1;
        return --playerX;
    }
}
