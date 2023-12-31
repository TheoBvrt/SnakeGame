package ch.bvrt.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SnakeGame");

        Canvas canvas = new Canvas(500, 500);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene =  new Scene(root);
        primaryStage.setScene(scene);

        SnakeGame snakeGame = new SnakeGame(10, 500, graphicsContext, scene);
        snakeGame.Run();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}