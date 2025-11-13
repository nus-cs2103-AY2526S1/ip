package zbot;

import javafx.application.Application;

/**
 * Launcher wrapper class to bypass JavaFX runtime checks when running from fat JAR.
 * This class serves as the entry point and delegates to the actual JavaFX Application.
 */
public class LauncherMain {
    /**
     * Main entry point that delegates to the JavaFX application.
     * This bypasses JavaFX module system checks that prevent fat JAR execution.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Launch the JavaFX Application
        Application.launch(zbot.gui.Main.class, args);
    }
}