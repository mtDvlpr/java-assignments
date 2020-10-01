package nl.inholland.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.model.*;

public class Dashboard {
    Stage window;

    public Dashboard(Person user) {
        window = new Stage();

        // Set Window properties
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Dashboard");

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Label testLabel = new Label("Label");
        TextField testInput = new TextField();
        Button testButton = new Button("Button");

        // Add attributes
        testInput.setPromptText("TextField");
        testButton.setDefaultButton(true);

        // When button is clicked
        testButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // some code
            }
        });

        // Add components to grid
        gridPane.add(testLabel, 0,0);
        gridPane.add(testInput, 0, 1);
        gridPane.add(testButton, 1, 0);

        // Set scene
        window.setScene(new Scene(gridPane));

        // Show window
        window.show();
    }
}
