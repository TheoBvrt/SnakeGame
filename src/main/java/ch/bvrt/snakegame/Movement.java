package ch.bvrt.snakegame;

public class Movement {
    public static int[] Forward (int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1};

        gameTab[playerY][playerX] = 0;
        gameTab[playerY - 1][playerX] = 2;
        returnTab[0] = --playerY;
        if (gameTab[playerY][playerX] == 5)
            returnTab[1] = 1;
        return returnTab;
    }

    public static int[] Right (int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1};

        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX + 1] = 2;
        returnTab[0] = ++playerX;
        if (gameTab[playerY][playerX] == 5)
            returnTab[1] = 1;
        return returnTab;
    }

    public static int[] Backward (int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1};

        gameTab[playerY][playerX] = 0;
        gameTab[playerY + 1][playerX] = 2;
        returnTab[0] = ++playerY;
        if (gameTab[playerY][playerX] == 5)
            returnTab[1] = 1;
        return returnTab;
    }

    public static int[] Left (int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1};

        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX - 1] = 2;
        returnTab[0] = --playerX;
        if (gameTab[playerY][playerX] == 5)
            returnTab[1] = 1;
        return returnTab;
    }
}
