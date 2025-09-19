package eureka;

import javafx.application.Application;

/**
 * A launcher class for the Eureka application.
 * <p>
 * This class exists as a workaround for JavaFX classpath issues.
 * Instead of running {@link Main} directly, the program starts
 * from this class, which then launches the JavaFX application.
 */
public class Launcher {

    /**
     * The main entry point of the program.
     * <p>
     * Delegates to {@link Application#launch(Class, String...)}
     * to start the JavaFX application defined in {@link Main}.
     *
     * @param args command-line arguments passed to the application
     */

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
