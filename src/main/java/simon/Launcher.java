package simon;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues when running JavaFX applications.
 * This class serves as an alternative entry point that avoids module path complications.
 */
public class Launcher {
    /**
     * Main method that launches the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
