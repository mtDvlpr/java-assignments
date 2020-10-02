package nl.inholland;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CarRental extends Application {
    @Override
    public void start(Stage window) {
        // Constants
        final int PRICE_PER_DAY = 45;
        final double PRICE_PER_KM = .25;
        final int PRICE_PER_LITER = 2;

        // Set Window properties
        window.setHeight(250);
        window.setWidth(400);
        window.setTitle("Car Rental");

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Label daysLabel = new Label("Number of days rented:");
        Label kmsLabel = new Label("Number of kilometers driven:");
        Label litersLabel = new Label("Number of liters:");
        TextField daysInput = new TextField();
        TextField kmsInput = new TextField();
        TextField litersInput = new TextField();
        CheckBox fuelTankFull = new CheckBox("Fuel tank not full when returned");
        Button calculateButton = new Button("Calculate Payment");
        Label amountDue = new Label("Amount due:");
        Label amountLabel = new Label();

        // Add attributes
        daysInput.setPromptText("Number of days...");
        kmsInput.setPromptText("Number of kilometers...");
        litersInput.setPromptText("Number of liters...");
        calculateButton.setDefaultButton(true);

        // Set Field limitations
        App.makeFieldIntegerOnly(daysInput);
        App.makeFieldNumeric(kmsInput);
        App.makeFieldNumeric(litersInput);

        // When button is clicked
        calculateButton.setOnAction(actionEvent -> {
            double total = 0;
            total += Integer.parseInt(daysInput.getText()) * PRICE_PER_DAY;
            double nrOfKm = Double.parseDouble(kmsInput.getText());
            if (nrOfKm > 100) {
                nrOfKm -= 100;
                total += nrOfKm * PRICE_PER_KM;
            }
            if (fuelTankFull.isSelected()) {
                total += Double.parseDouble(litersInput.getText()) * PRICE_PER_LITER;
            }
            amountLabel.textProperty().setValue(String.format("%.2f", total));
        });

        // Add components to grid
        gridPane.add(daysLabel, 0, 0);
        gridPane.add(kmsLabel, 0, 1);
        gridPane.add(litersLabel, 0, 3);
        gridPane.add(daysInput, 1, 0);
        gridPane.add(kmsInput, 1, 1);
        gridPane.add(litersInput, 1, 3);
        gridPane.add(fuelTankFull, 0, 2);
        gridPane.add(calculateButton, 1, 4);
        gridPane.add(amountDue, 0, 5);
        gridPane.add(amountLabel, 1, 5);

        // Set scene
        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        // Show window
        window.show();
    }
}
