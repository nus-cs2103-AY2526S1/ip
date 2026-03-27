package marvin.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues
 */
public class Launcher {
    /**
     * Used as an entrypoint to the GUI app.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
