package nl.inholland;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage window) throws Exception {
        // Set Window properties
        window.setHeight(430);
        window.setWidth(350);
        window.setTitle("Tic-tac-toe");

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Button square1 = new Button("_");
        Button square2 = new Button("_");
        Button square3 = new Button("_");
        Button square4 = new Button("_");
        Button square5 = new Button("_");
        Button square6 = new Button("_");
        Button square7 = new Button("_");
        Button square8 = new Button("_");
        Button square9 = new Button("_");
        Label currentTurnLabel = new Label("Current turn: ");
        Label currentTurn = new Label("X");

        // Add attributes
        square1.setMinSize(100, 100);
        square2.setMinSize(100, 100);
        square3.setMinSize(100, 100);
        square4.setMinSize(100, 100);
        square5.setMinSize(100, 100);
        square6.setMinSize(100, 100);
        square7.setMinSize(100, 100);
        square8.setMinSize(100, 100);
        square9.setMinSize(100, 100);

        // When button is clicked
        square1.setOnAction(actionEvent -> buttonClicked(gridPane, square1));
        square2.setOnAction(actionEvent -> buttonClicked(gridPane, square2));
        square3.setOnAction(actionEvent -> buttonClicked(gridPane, square3));
        square4.setOnAction(actionEvent -> buttonClicked(gridPane, square4));
        square5.setOnAction(actionEvent -> buttonClicked(gridPane, square5));
        square6.setOnAction(actionEvent -> buttonClicked(gridPane, square6));
        square7.setOnAction(actionEvent -> buttonClicked(gridPane, square7));
        square8.setOnAction(actionEvent -> buttonClicked(gridPane, square8));
        square9.setOnAction(actionEvent -> buttonClicked(gridPane, square9));

        // Add components to grid
        gridPane.add(square1, 0,0);
        gridPane.add(square2, 1,0);
        gridPane.add(square3, 2,0);
        gridPane.add(square4, 0,1);
        gridPane.add(square5, 1,1);
        gridPane.add(square6, 2,1);
        gridPane.add(square7, 0,2);
        gridPane.add(square8, 1,2);
        gridPane.add(square9, 2,2);

        HBox turnContainer = new HBox();
        turnContainer.setPadding(new Insets(10));
        turnContainer.getChildren().addAll(currentTurnLabel, currentTurn);

        VBox container = new VBox();
        container.setSpacing(10);
        container.getChildren().addAll(gridPane, turnContainer);

        // Set scene
        Scene scene = new Scene(container);
        window.setScene(scene);

        // Show window
        window.show();
    }

    private void buttonClicked(GridPane buttons, Button button) {
        if (button.getText().equals("_")) {
            button.textProperty().setValue("X");
            if (!checkForWinner(buttons, "X")) {
                doComputerMove(buttons);
                checkForWinner(buttons, "O");
            }
        }
    }

    private void doComputerMove(GridPane buttons) {
        for (Node b : buttons.getChildren()) {
            if (((Button)b).textProperty().getValue().equals("_")) {
                Random rnd = new Random();
                int index;
                Button btn;
                do {
                    index = rnd.nextInt(buttons.getChildren().size());
                    btn = (Button)buttons.getChildren().get(index);
                }
                while (!btn.textProperty().getValue().equals("_"));

                btn.textProperty().setValue("O");
                break;
            }
        }
    }

    private boolean checkForWinner(GridPane buttons, String player) {
        for (int i = 0; i < buttons.getChildren().size(); i++) {

        }

        return false;
    }
}
