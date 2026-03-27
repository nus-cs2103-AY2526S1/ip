package gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This is needed because JavaFX has some special requirements.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
