package nl.inholland.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.logic.Person_Service;
import nl.inholland.model.*;
import nl.inholland.ui.Dashboard;

public class App extends Application {
    private Person_Service personService = new Person_Service();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        // Set Window properties
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Login Screen");

        // Set grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        // Create components
        Label userLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField userInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("Log in");

        // Add attributes
        userInput.setPromptText("Enter your username...");
        passwordInput.setPromptText("Enter your password...");
        loginButton.setDefaultButton(true);

        // When button is clicked
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Person user = personService.validateUser(userInput.getText(), passwordInput.getText());
                if (user != null) {
                    new Dashboard(user);
                }
                else {
                    new Alert(Alert.AlertType.WARNING, "The username and password do not match! Try again.");
                }
            }
        });

        // Add components to grid
        gridPane.add(userLabel, 0,0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(userInput, 1, 0);
        gridPane.add(passwordInput, 1, 1);
        gridPane.add(loginButton, 1, 2);

        // Set scene
        window.setScene(new Scene(gridPane));

        // Show window
        window.show();
    }
}