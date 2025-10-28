package userinteraction;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This is also where the program starts to run from.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(MainScene.class, args);
    }
}
