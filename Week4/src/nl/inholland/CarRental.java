package nl.inholland;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CarRental extends Application {
    @Override
    public void start(Stage window) throws Exception {
        // Set Window properties
        window.setHeight(500);
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
        Label amountLabel = new Label();

        // Add attributes
        daysInput.setPromptText("Number of days...");
        kmsInput.setPromptText("Number of kilometers...");
        litersInput.setPromptText("Number of liters...");
        calculateButton.setDefaultButton(true);

        // When button is clicked
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION, "Button has been clicked!");
            }
        });

        // Add components to grid
        gridPane.add(daysLabel, 0,0);
        gridPane.add(kmsLabel, 0, 1);
        gridPane.add(litersLabel, 0, 3);
        gridPane.add(daysInput, 1, 0);
        gridPane.add(kmsInput, 1, 1);
        gridPane.add(litersInput, 1, 3);
        gridPane.add(fuelTankFull, 0, 2);
        gridPane.add(calculateButton, 1, 4);
        gridPane.add(amountLabel, 0, 5);

        // Set scene
        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        // Show window
        window.show();
    }
}
