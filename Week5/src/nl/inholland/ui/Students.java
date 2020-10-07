package nl.inholland.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.logic.Person_Service;
import nl.inholland.model.LevelOfAccess;
import nl.inholland.model.Person;
import nl.inholland.model.Student;

import java.time.LocalDate;

public class Students {
    private final Stage window;

    public Students(Person user) {
        window = new Stage();
        Person_Service personService = new Person_Service();
        ObservableList<Student> students = FXCollections.observableList(personService.getStudents());

        // Set Window properties
        window.setHeight(800);
        window.setWidth(1024);
        window.setTitle("Students");

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

        addButton.setOnAction(actionEvent -> {
            new AddStudent(user);
            window.close();
        });

        editButton.setOnAction(actionEvent -> {
            if (!tableView.getSelectionModel().isEmpty()) {
                new EditStudent(user, students.get(students.indexOf(tableView.getSelectionModel().getSelectedItem())));
                window.close();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "Please select a student to edit.").show();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            if (!tableView.getSelectionModel().isEmpty()) {
                Student student = tableView.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + student.firstName + " " + student.lastName + "?", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    students.remove(student);
                    personService.saveStudents(students);
                }
                else {
                    tableView.getSelectionModel().clearSelection();
                }
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "Please select a student to delete.").show();
            }
        });

        // Add components to its container
        menuBar.getMenus().addAll(dashboardMenu, studentsMenu, teachersMenu);
        if (user.levelOfAccess != LevelOfAccess.Basic) { buttons.getChildren().addAll(addButton, editButton, deleteButton); }
        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, birthdateColumn, ageColumn, groupColumn);
        tableView.setItems(students);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        content.getChildren().addAll(title, tableView, buttons);
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
