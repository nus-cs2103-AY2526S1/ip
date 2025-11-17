package sophia;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        //System.out.println("Assertions enabled? " + Launcher.class.desiredAssertionStatus());
        Application.launch(Main.class, args);
    }
}
