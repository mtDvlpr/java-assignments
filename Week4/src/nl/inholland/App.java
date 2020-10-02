package nl.inholland;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void makeFieldNumeric(TextField textField) {

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

    public static void makeFieldIntegerOnly(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void start(Stage window) {
        // Start Currency Converter App
        CurrencyConverter converter = new CurrencyConverter();
        converter.start(window);

        // Start Car Rental App
        CarRental carRental = new CarRental();
        carRental.start(window);

        // Start Tic-tac-toe App
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.start(window);
    }
}
