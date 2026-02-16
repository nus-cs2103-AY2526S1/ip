package shadow.ui;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point to the application.
 * It is responsible for launching the JavaFX application by invoking the {@code Application.launch} method
 * with the main application class, {@code Main}, and the provided runtime arguments.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
