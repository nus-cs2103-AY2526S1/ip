package aurora;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("cli")) {
            new aurora.cli.AuroraCli().run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
