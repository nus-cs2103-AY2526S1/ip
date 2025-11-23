package Mithrandir.ui;

import javafx.application.Application;

/**
 * Launcher class for the Mithrandir application.
 * This class serves as the entry point for the JavaFX application.
 */
public class Launcher {
    /**
     * The main entry point for the Mithrandir application.
     * Launches the JavaFX application by starting the GUi class.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Application.launch(GUi.class, args);
    }
}
