package chash;

import javafx.application.Application;

/** A launcher class to workaround classpath issues. */
public class ChashLauncher {
    /**
     * GUI only application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        //Chash.main(args);
        Application.launch(Chash.class, args);
    }
}
