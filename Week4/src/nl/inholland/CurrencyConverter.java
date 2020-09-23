package nl.inholland;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class CurrencyConverter extends Application {

    @Override
    public void start(Stage window) throws Exception {
        final double RATE = 1.18;

        // Set Window properties
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Currency Converter");
        window.setX(100);

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Label euroLabel = new Label("Amount €:");
        Label dollarLabel = new Label("Amount $:");
        TextField userInput = new TextField();
        Button convertButton = new Button("Convert Euro to Dollar");
        Label amountLabel = new Label();

        makeFieldNumeric(userInput);
        userInput.setPromptText("Enter an amount...");
        convertButton.setDefaultButton(true);

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (userInput.getText().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Enter an amount!").show();
                }
                else {
                    amountLabel.textProperty().setValue(String.format("%.2f", Double.parseDouble(userInput.getText()) * RATE));
                }
            }
        });

        // Add components to grid
        gridPane.add(euroLabel, 0,0);
        gridPane.add(dollarLabel, 0, 2);
        gridPane.add(userInput, 1, 0);
        gridPane.add(convertButton, 1, 1);
        gridPane.add(amountLabel, 1, 2);

        // Set scene
        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        // Show window
        window.show();
    }

    private static void makeFieldNumeric(TextField textField) {

        DecimalFormat format = new DecimalFormat("#");

        final TextFormatter<Object> decimalTextFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().isEmpty()) {
                return change;
            }
            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(change.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < change.getControlNewText().length() || change.getControlNewText().length() > 9) {
                return null;
            } else {
                return change;
            }
        });
        textField.setTextFormatter(decimalTextFormatter);
    }
}
