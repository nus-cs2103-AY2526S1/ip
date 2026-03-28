package lenny.gui;

import javafx.application.Application;

/**
 * Plain Java launcher used to start the JavaFX {@link Gui} application.
 * <p>
 * Having a separate launcher class avoids issues starting JavaFX when the
 * {@code Application} subclass is not launched directly by the runtime.
 * </p>
 */
public class Launcher {
    /**
     * Delegates to main in {@link Gui} class to start application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
