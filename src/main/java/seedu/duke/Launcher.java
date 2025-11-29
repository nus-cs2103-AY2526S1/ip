package seedu.duke;

import javafx.application.Application;

/**
 * Launches JavaFX in environments where running the {@link Main} class directly
 * can cause classpath issues.
 */
public final class Launcher {

    private Launcher() {
        // Utility class.
    }

    /**
     * Entry point that delegates to {@link Main}.
     *
     * @param args Program arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
