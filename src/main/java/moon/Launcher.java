package moon;

import javafx.application.Application;

/**
 * Entry point of the program.
 * <p>
 * Delegates to {@link Main}, which is the actual JavaFX {@link Application}.
 * This indirection avoids classpath issues with JavaFX when packaging with Gradle.
 */
public class Launcher {
    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
