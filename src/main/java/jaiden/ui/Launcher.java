package jaiden.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point to application.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
