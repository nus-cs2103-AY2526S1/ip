package sage;

import javafx.application.Application;
import sage.gui.Main;

/**
 * A launcher class to work around classpath issues.
 * This allows the JAR to be run using sage.Launcher as the main class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}