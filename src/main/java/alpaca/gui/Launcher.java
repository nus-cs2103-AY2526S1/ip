package alpaca.gui;

import javafx.application.Application;

/**
 * Launcher class for the Alpaca GUI application.
 * <p>
 * Used to work around JavaFX classpath issues by providing a separate
 * entry point to launch the Main JavaFX application.
 */
public class Launcher {

    /**
     * Main entry point for the Alpaca GUI application.
     * Delegates to {@link Main} to start the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
