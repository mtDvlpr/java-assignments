package nl.inholland;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {
    private Stage window;

    @Override
    public void start(Stage window) {
        // Set Window properties
        this.window = window;
        window.setHeight(430);
        window.setWidth(350);
        window.setTitle("Tic-tac-toe");

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Button[][] buttons = new Button[3][3];
        Label currentTurnLabel = new Label("Current turn: ");
        Label currentTurn = new Label("X");

        // Create buttons and add them to the grid
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[0].length; col++) {
                Button button = new Button("_");
                button.setMinSize(100, 100);
                button.setOnAction(actionEvent -> buttonClicked(buttons, button));
                gridPane.add(button, row, col);
                buttons[row][col] = button;
            }
        }

        // Add components to grid
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

    private void buttonClicked(Button[][] buttons, Button button) {
        if (button.getText().equals("_")) {
            button.textProperty().setValue("X");

            if (checkForWinner(buttons, "X")) {
                showMessage("X won!");
            } else if (checkForTie(buttons)) {
                showMessage("Tie!");
            } else {
                doComputerMove(buttons);
                if (checkForWinner(buttons, "O")) {
                    showMessage("O won!");
                }
            }
        }
    }

    private void doComputerMove(Button[][] buttons) {
        for (Button[] button : buttons) {
            for (int col = 0; col < buttons[0].length; col++) {
                if (button[col].textProperty().getValue().equals("_")) {
                    Random rnd = new Random();
                    int btnRow;
                    int btnCol;
                    Button btn;
                    do {
                        btnRow = rnd.nextInt(buttons.length);
                        btnCol = rnd.nextInt(buttons[0].length);
                        btn = buttons[btnRow][btnCol];
                    }
                    while (!btn.textProperty().getValue().equals("_"));

                    btn.textProperty().setValue("O");
                    return;
                }
            }
        }
    }

    private boolean checkForWinner(Button[][] buttons, String player) {
        return checkRowColWin(buttons, player, true) || checkRowColWin(buttons, player, false) || checkDiagonalWin(buttons, player);
    }

    private boolean checkRowColWin(Button[][] buttons, String player, boolean checkRow) {
        for (int row = 0; row < buttons.length; row++) {
            int inARow = 0;
            for (int col = 0; col < buttons[0].length; col++) {
                if (checkRow) {
                    if (buttons[row][col].textProperty().getValue().equals(player)) {
                        inARow++;
                    }
                } else {
                    if (buttons[col][row].textProperty().getValue().equals(player)) {
                        inARow++;
                    }
                }
            }
            if (inARow == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(Button[][] buttons, String player) {
        return diagonalWin(player, buttons[0][0], buttons[1][1], buttons[2][2]) || diagonalWin(player, buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean diagonalWin(String player, Button btn1, Button btn2, Button btn3) {
        return btn1.textProperty().getValue().equals(player) && btn2.textProperty().getValue().equals(player) && btn3.textProperty().getValue().equals(player);
    }

    private boolean checkForTie(Button[][] buttons) {
        for (Button[] button : buttons) {
            for (int col = 0; col < buttons[0].length; col++) {
                if (button[col].textProperty().getValue().equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
        new TicTacToe().start(new Stage());
        window.close();
    }
}