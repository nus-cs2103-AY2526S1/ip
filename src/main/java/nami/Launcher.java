package nami;

import javafx.application.Application;
import nami.ui.Main;

/**
 * Launcher class to workaround JavaFX classpath issues when running from a fat JAR.
 * Placed in the main nami package
 */
public class Launcher {
    /**
     * Main entry point.
     * Delegates to the JavaFX Main class.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
