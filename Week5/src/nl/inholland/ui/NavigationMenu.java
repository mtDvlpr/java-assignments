package nl.inholland.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import nl.inholland.model.Person;

public class NavigationMenu {
    private static MenuBar menuBar = new MenuBar();
    private static Menu dashboardMenu = new Menu("Dashboard");
    private static Menu studentsMenu = new Menu("Students");
    private static Menu teachersMenu = new Menu("Teachers");
    private static Menu logoutMenu = new Menu("Log Out");

    public static MenuBar getMenu(Stage window, Person user) {
        onAction(dashboardMenu);
        onAction(studentsMenu);
        onAction(teachersMenu);
        onAction(logoutMenu);

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

        logoutMenu.setOnAction(actionEvent -> {
            new App().start(new Stage());
            window.close();
        });

        menuBar.getMenus().addAll(dashboardMenu, studentsMenu, teachersMenu, logoutMenu);

        return menuBar;
    }

    private static void onAction(Menu menu) {
        final MenuItem menuItem = new MenuItem();

        menu.getItems().add(menuItem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }
}
