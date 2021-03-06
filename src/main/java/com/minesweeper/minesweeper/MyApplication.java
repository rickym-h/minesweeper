package com.minesweeper.minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        HBox topRoot = new HBox();
        topRoot.setAlignment(Pos.CENTER);
        VBox root = new VBox();


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
                informationLabel.setText("Welcome to my minesweeper game!");
            }
        });

        updateFieldGrid();

        HBox newGamePane = new HBox();



        difficultyComboBox.getItems().add("Easy");
        difficultyComboBox.getItems().add("Medium");
        difficultyComboBox.getItems().add("Hard");

        newGamePane.getChildren().addAll(difficultyComboBox, button);
        // Add nodes to the root
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(newGamePane,fieldGrid,informationLabel);
        //root.setTop(newGamePane);
        //root.setCenter(fieldGrid);
        //root.setBottom(informationLabel);

        // Create a new window using the root and all it's sub-nodes
        topRoot.getChildren().add(root);
        Scene scene = new Scene(topRoot, 960, 720);

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

        int recWidth = (600 / myField.width) - 3;
        int recHeight = (600 / myField.height) - 3;

        Color grassColour1 = Color.web("a2d149");
        Color grassColour2 = Color.web("aad751");
        Color dugColour1 = Color.web("d7b899");
        Color dugColour2 = Color.web("e5c29f");
        Color mineColour = Color.web("000000");
        Color markColour = Color.web("FF0000");

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
                    if (tile.getNumOfAdjacentMines()>0) {
                        tileLabel.setText(String.valueOf(tile.getNumOfAdjacentMines()));
                    }
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
                            System.out.println(t);
                            if (gameOver) {
                                return;
                            }
                            if (t.getButton() == MouseButton.SECONDARY) {
                                tile.toggleMarked();
                                updateFieldGrid();
                                return;
                            }
                            myField.clickTile(tile.getCoord());
                            System.out.println(tile.toString());
                            if (tile.getTileType() == Tile.Type.MINE) {
                                informationLabel.setText("YOU LOSE");
                                for (Tile tile : myField.tiles.values()) {
                                    if (tile.getTileType() == Tile.Type.MINE) {
                                        tile.clickTile();
                                    }
                                }
                                gameOver = true;
                            }

                            if (myField.isWon()) {
                                informationLabel.setText("YOU WIN!");
                                gameOver = true;
                            }
                            updateFieldGrid();
                        }
                    });
                }


                if (tile.isMarked() && !tile.getVisibility()) {
                    Circle circleMark = new Circle(5);
                    circleMark.setFill(markColour);
                    tilePane.getChildren().addAll(rectangle, circleMark);
                } else {
                    tilePane.getChildren().addAll(rectangle, tileLabel);
                }


                fieldGrid.add(tilePane, x, myField.height - y);
            }
        }
    }
}