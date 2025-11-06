package v;

import javafx.application.Application;

/**
 * A launcher class to work around classpath/JavaFX startup quirks.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
