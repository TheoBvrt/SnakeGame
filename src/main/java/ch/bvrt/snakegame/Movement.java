package ch.bvrt.snakegame;

public class Movement {
    public static int[] Forward(int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1, -1};

        if (gameTab[playerY - 1][playerX] == 5)
            returnTab[1] = 1;
        if (gameTab[playerY - 1][playerX] == 1 || gameTab[playerY - 1][playerX] == 10)
            returnTab[2] = 1;
        gameTab[playerY][playerX] = 0;
        gameTab[playerY - 1][playerX] = 2;
        returnTab[0] = playerY - 1;
        return (returnTab);
    }

    public static int[] Right(int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1, 0};

        if (gameTab[playerY][playerX + 1] == 5)
            returnTab[1] = 1;
        if (gameTab[playerY][playerX + 1] == 1 || gameTab[playerY][playerX + 1] == 10) {
            returnTab[2] = 1;
        }
        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX + 1] = 2;
        returnTab[0] = playerX + 1;
        return (returnTab);
    }

    public static int[] Backward(int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1, 0};

        if (gameTab[playerY + 1][playerX] == 5)
            returnTab[1] = 1;
        if (gameTab[playerY + 1][playerX] == 1 || gameTab[playerY + 1][playerX] == 10)
            returnTab[2] = 1;
        gameTab[playerY][playerX] = 0;
        gameTab[playerY + 1][playerX] = 2;
        returnTab[0] = playerY + 1;
        return (returnTab);
    }

    public static int[] Left(int[][] gameTab, int playerY, int playerX) {
        int[] returnTab = {-1, -1, 0};

        if (gameTab[playerY][playerX - 1] == 5)
            returnTab[1] = 1;
        if (gameTab[playerY][playerX - 1] == 1 || gameTab[playerY][playerX - 1] == 10)
            returnTab[2] = 1;
        gameTab[playerY][playerX] = 0;
        gameTab[playerY][playerX - 1] = 2;
        returnTab[0] = playerX - 1;
        return (returnTab);
    }
}
