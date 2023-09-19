package ch.bvrt.snakegame;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FrameUpdater extends AnimationTimer {
    private final int[][] gameTab;
    private final GraphicsContext gc;
    private final int spriteSize;

    public FrameUpdater(int[][] gameTab, GraphicsContext graphicsContext, int spriteSize) {
        this.gameTab = gameTab;
        this.gc = graphicsContext;
        this.spriteSize = spriteSize;
    }

    @Override
    public void handle(long now) {
        frameUpdate(gameTab);
    }

    public void frameUpdate(int[][] gameTab) {
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
                } else if (ints[gameTabY] == 10) {
                    DrawCell(frameY, frameX, Color.BLUEVIOLET);
                }
                frameX += spriteSize;
            }
            frameY += spriteSize;
            frameX = 0;
        }
        DrawCell(0, 0, Color.BLUEVIOLET);
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
