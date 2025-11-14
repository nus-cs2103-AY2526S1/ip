import javafx.application.Application;

/**
 * Launcher class for the Baymax application.
 * <p>
 * This class serves as a workaround for certain JavaFX classpath issues
 * when running the application. It simply delegates to {@link Main}.
 */
public class Launcher {
    /**
     * The main entry point of the launcher.
     *
     * @param args command-line arguments (optional)
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
