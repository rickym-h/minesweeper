module com.minesweeper.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.minesweeper.minesweeper to javafx.fxml;
    exports com.minesweeper.minesweeper;
}