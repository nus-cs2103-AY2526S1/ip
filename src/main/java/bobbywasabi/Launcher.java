package bobbywasabi;

import javafx.application.Application;

/**
 * The launcher class for the BobbyWasabi JavaFX application.
 * <p>
 * This class serves as the entry point to launch the JavaFX application by invoking
 * the {@link Application#launch(String...)} method with the {@link Main} class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
