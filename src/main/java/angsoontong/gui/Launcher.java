package angsoontong.gui;
import javafx.application.Application;

public class Launcher {
    /**
     * A launcher class to workaround classpath issues.
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, args); // hands off to the JavaFX Application below
    }
}
