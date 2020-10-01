package nl.inholland.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.inholland.logic.Person_Service;
import nl.inholland.model.*;

import java.time.LocalDate;
import java.util.List;

public class Students {
    private Stage window;
    private final Person_Service personService;
    private ObservableList<Student> students;

    public Students(Person user) {
        window = new Stage();
        personService = new Person_Service();
        students = FXCollections.observableList(personService.getStudents());

        // Set Window properties
        window.setHeight(800);
        window.setWidth(1024);
        window.setTitle("Dashboard");

        // Set containers
        BorderPane container = new BorderPane();
        VBox content = new VBox(10);
        HBox buttons = new HBox(10);

        // Create components
        MenuBar menuBar = new MenuBar();
        Menu dashboardMenu = new Menu("Dashboard");
        Menu studentsMenu = new Menu("Students");
        Menu teachersMenu = new Menu("Teachers");

        Label title = new Label("Students");
        TableView<Student> tableView = new TableView<>();

        TableColumn<Student, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(features -> new SimpleIntegerProperty(features.getValue().id).asObject());

        TableColumn<Student, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(features -> new SimpleStringProperty(features.getValue().firstName));

        TableColumn<Student, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(features -> new SimpleStringProperty(features.getValue().lastName));

        TableColumn<Student, LocalDate> birthdateColumn = new TableColumn<>("Birth date");
        birthdateColumn.setCellValueFactory(features -> new SimpleObjectProperty<>(features.getValue().birthdate));

        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(features -> new SimpleIntegerProperty(features.getValue().getAge()).asObject());

        TableColumn<Student, String> groupColumn = new TableColumn<>("Group");
        groupColumn.setCellValueFactory(features -> new SimpleStringProperty(features.getValue().group));

        Button addButton = new Button("Add student");
        Button editButton = new Button("Edit student");
        Button deleteButton = new Button("Delete Student");

        // Add attributes
        tableView.setPlaceholder(new Label("No rows to display"));
        content.setPadding(new Insets(10));
        title.setFont(new Font(30));
        onAction(dashboardMenu);
        onAction(studentsMenu);
        onAction(teachersMenu);

        // When button is clicked
        dashboardMenu.setOnAction(actionEvent -> {
            new Students(user);
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
        buttons.getChildren().addAll(addButton, editButton, deleteButton);
        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, birthdateColumn, ageColumn, groupColumn);
        tableView.setItems(students);

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );

        content.getChildren().addAll(title, tableView, buttons);
        container.setTop(menuBar);
        container.setCenter(content);

        // Set scene
        Scene scene = new Scene(container);
        scene.getStylesheets().add("resources/style.css");
        window.setScene(scene);

        // Show window
        window.show();
    }

    public static void onAction(Menu menu)
    {
        final MenuItem menuItem = new MenuItem();

        menu.getItems().add(menuItem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }
}
