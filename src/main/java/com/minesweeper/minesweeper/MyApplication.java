package com.minesweeper.minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MyApplication extends Application {
    //MinesweeperField myField = new MinesweeperField(10,8,10);
    //MinesweeperField myField = new MinesweeperField(10,8,10);
    MinesweeperField myField = new MinesweeperField(18,14,40);
    GridPane fieldGrid = new GridPane();
    Label informationLabel = new Label("Welcome to my minesweeper game!");
    ComboBox<String> difficultyComboBox = new ComboBox<String>();

    boolean gameOver = false;


    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();


        // Set the title of the window
        primaryStage.setTitle("Ricky's Minesweeper Game");

        // Create a new button which logs to console when clicked
        Button button = new Button("New Game!");
        button.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String value = (String) difficultyComboBox.getValue();
                System.out.println(value);
                switch (value) {
                    case "Easy" -> myField = new MinesweeperField(10, 8, 10);
                    case "Medium" -> myField = new MinesweeperField(18, 14, 40);
                    case "Hard" -> myField = new MinesweeperField(24, 20, 99);
                    default -> {
                        informationLabel.setText("Please use the dropdown menu to enter a valid difficulty!");
                        return;
                    }
                }
                updateFieldGrid();
                gameOver = false;
            }
        });

        updateFieldGrid();

        HBox newGamePane = new HBox();



        difficultyComboBox.getItems().add("Easy");
        difficultyComboBox.getItems().add("Medium");
        difficultyComboBox.getItems().add("Hard");

        newGamePane.getChildren().addAll(difficultyComboBox, button);
        // Add nodes to the root
        root.setTop(newGamePane);
        root.setCenter(fieldGrid);
        root.setBottom(informationLabel);

        // Create a new window using the root and all it's sub-nodes
        Scene scene = new Scene(root, 960, 720);

        // Set scene and show the window
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void updateFieldGrid() {
        fieldGrid.getChildren().clear();

        int recWidth = 30;
        recWidth = (int)(600 / myField.width) - 3;
        int recHeight = 30;
        recHeight = (int)(600 / myField.height) - 3;

        Color grassColour1 = Color.web("a2d149");
        Color grassColour2 = Color.web("aad751");
        Color dugColour1 = Color.web("d7b899");
        Color dugColour2 = Color.web("e5c29f");
        Color mineColour = Color.web("000000");

        for (int x = 0; x < myField.width; x++) {
            for (int y = 0; y < myField.height; y++) {
                StackPane tilePane = new StackPane();
                Label tileLabel = new Label();
                Rectangle rectangle = new Rectangle(recWidth, recHeight);
                CoordTuple coord = new CoordTuple(x, y);
                Tile tile = myField.tiles.get(coord);

                if (tile.getVisibility()) {
                    if ((tile.getX()+tile.getY()) % 2 == 0) {
                        rectangle.setFill(dugColour2);
                    } else {
                        rectangle.setFill(dugColour1);
                    }
                    tileLabel.setText(String.valueOf(tile.getNumOfAdjacentMines()));
                    if (tile.getTileType() == Tile.Type.MINE) {
                        rectangle.setFill(mineColour);
                    }
                } else {
                    if ((tile.getX()+tile.getY()) % 2 == 0) {
                        rectangle.setFill(grassColour2);
                    } else {
                        rectangle.setFill(grassColour1);
                    }
                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent t) {
                            if (gameOver) {
                                return;
                            }
                            myField.clickTile(tile.getCoord());
                            System.out.println(tile.toString());
                            if (tile.getTileType() == Tile.Type.MINE) {
                                informationLabel.setText("YOU LOSE");
                                gameOver = true;
                            }
                            updateFieldGrid();
                        }
                    });
                }




                tilePane.getChildren().addAll(rectangle, tileLabel);
                fieldGrid.add(tilePane, x, myField.height - y);
            }
        }
        return;
    }
}