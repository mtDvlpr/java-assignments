package nl.inholland.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.logic.Person_Service;
import nl.inholland.model.Person;

public class AddStudent {
    private final Stage window;

    public AddStudent(Person user) {
        window = new Stage();
        Person_Service personService = new Person_Service();

        // Set Window properties
        window.setHeight(800);
        window.setWidth(1024);
        window.setTitle("Add Student");

        // Set containers
        BorderPane container = new BorderPane();
        VBox content = new VBox(10);
        HBox buttons = new HBox(10);
        GridPane grid = new GridPane();

        // Create components
        MenuBar menuBar = new MenuBar();
        Menu dashboardMenu = new Menu("Dashboard");
        Menu studentsMenu = new Menu("Students");
        Menu teachersMenu = new Menu("Teachers");

        Label title = new Label("Add Student");
        Button addButton = new Button("Add Student");
        Button cancelButton = new Button("Cancel");

        TextField userInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        ComboBox groupInput = new ComboBox();
        DatePicker dateInput = new DatePicker();

        // Add attributes
        content.setPadding(new Insets(10));
        title.setFont(new Font(30));
        onAction(dashboardMenu);
        onAction(studentsMenu);
        onAction(teachersMenu);

        groupInput.setItems(FXCollections.observableList(personService.getGroups()));

        // When button is clicked
        dashboardMenu.setOnAction(actionEvent -> {
            new Dashboard(user);
            window.close();
        });

        studentsMenu.setOnAction(actionEvent -> {
            new Students(user);
            window.close();
        });

        teachersMenu.setOnAction(actionEvent -> {
            new Teachers(user);
            window.close();
        });

        // Add components to its container
        menuBar.getMenus().addAll(dashboardMenu, studentsMenu, teachersMenu);

        grid.add(userInput, 0,0);
        grid.add(dateInput, 1,0);
        grid.add(passwordInput, 0,1);
        grid.add(firstNameInput, 0,2);
        grid.add(lastNameInput, 0,3);
        grid.add(groupInput, 0,4);
        grid.add(buttons, 0,5);

        content.getChildren().addAll(title, grid);
        container.setTop(menuBar);
        container.setCenter(content);

        // Set scene
        Scene scene = new Scene(container);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);

        // Show window
        window.show();
    }

    public static void onAction(Menu menu) {
        final MenuItem menuItem = new MenuItem();

        menu.getItems().add(menuItem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }
}
