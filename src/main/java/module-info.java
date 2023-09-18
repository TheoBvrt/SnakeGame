module ch.bvrt.snakegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.bvrt.snakegame to javafx.fxml;
    exports ch.bvrt.snakegame;
}