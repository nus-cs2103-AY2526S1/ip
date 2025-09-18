import javafx.application.Application;

/**
 * Entry point that defaults to launching the JavaFX GUI, but can run the CLI
 * when {@code --cli} is provided.
 *
 */
public class Launcher {

    /** Creates a new {@code Launcher}. */
    public Launcher() { }

    /**
     * Starts Jackbot in GUI mode by default. Use {@code --cli} to run the console UI.
     * @param args command-line arguments (use {@code --cli} for console mode)
     */
    public static void main(String[] args) {
        if (isCli(args)) {
            // Run the original console app (shares core with the GUI)
            jackbot.Jackbot app = new jackbot.Jackbot("./tasks.txt");
            app.run();
        } else {
            // Default to JavaFX GUI
            Application.launch(jackbot.fx.Main.class, args);
        }
    }

    private static boolean isCli(String[] args) {
        if (args == null || args.length == 0) {
            return false;
        }
        for (String a : args) {
            if ("--cli".equalsIgnoreCase(a)) {
                return true;
            }
        }
        return false;
    }
}
