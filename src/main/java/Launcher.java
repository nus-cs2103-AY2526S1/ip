import javafx.application.Application;

/**
 * Application entry point that delegates to either the console (CLI) or
 * JavaFX GUI depending on the command-line arguments.
 * <p>
 * By default, the JavaFX GUI is launched and the task list is stored in
 * {@code ./tasks.txt}. Use {@code --cli} to run the console interface
 * instead of the GUI, and {@code --file=&lt;path&gt;} to override the
 * default storage file.
 * <ul>
 *   <li>{@code java Launcher} → starts the GUI with {@code ./tasks.txt}</li>
 *   <li>{@code java Launcher --cli} → starts the CLI with {@code ./tasks.txt}</li>
 *   <li>{@code java Launcher --file=/tmp/tasks.txt} → starts the GUI with a custom file</li>
 *   <li>{@code java Launcher --cli --file=/tmp/tasks.txt} → starts the CLI with a custom file</li>
 * </ul>
 */
public class Launcher {

    /** Default task file path when none is specified via {@code --file}. */
    private static final String DEFAULT_FILE = "./tasks.txt";

    /** Constructs a new {@code Launcher}. */
    public Launcher() { }

    /**
      * Entry point that defaults to launching the JavaFX GUI, but can run the CLI
      * when {@code --cli} is provided. Also allows specifying custom task file path
      * to save or load tasks from via {@code --file}.
      * @param args command line arguments
      */
    public static void main(String[] args) {
        boolean cli = false;
        String file = DEFAULT_FILE;

        if (args != null) {
            for (String a : args) {
                if ("--cli".equalsIgnoreCase(a)) {
                    cli = true;
                } else if (a.startsWith("--file=")) {
                    file = a.substring("--file=".length());
                }
            }
        }

        if (cli) {
            jackbot.Jackbot app = new jackbot.Jackbot(file);
            app.run();
        } else {
            // Pass file path on to JavaFX
            Application.launch(jackbot.fx.Main.class, "--file=" + file);
        }
    }
}
