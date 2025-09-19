package chash;

import javafx.application.Application;

/** A launcher class to workaround classpath issues. */
public class ChashLauncher {
    public static void main(String[] args) {
        Chash.main(args);
        //Application.launch(Chash.class, args);
    }
}
