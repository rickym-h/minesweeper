package com.minesweeper.minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MyApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        GridPane fieldGrid = new GridPane();

        // Set the title of the window
        primaryStage.setTitle("Ricky's Minesweeper Game");

        // Create a new button which logs to console when clicked
        Button button = new Button("Click Me");
        button.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked!");
            }
        });

        // Add nodes to the root
        root.getChildren().add(button);

        // Todo create field and display
        MinesweeperField myField = new MinesweeperField(3, 3, 1);

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                Tile myTile = myField.tiles.get(new CoordTuple(i, j));
//                System.out.println(String.valueOf(i) + String.valueOf(j) + "-" + String.valueOf(myTile.numOfAdjacentMines));
//            }
//        }

        // Create a new window using the root and all it's sub-nodes
        Scene scene = new Scene(root, 480, 480);

        // Set scene and show the window
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}