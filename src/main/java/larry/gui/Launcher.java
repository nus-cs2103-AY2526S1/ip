package larry.gui;

/**
 * JavaFX bootstrapper.
 * <p>
 * Sets platform hints for JavaFX and delegates to {@link Main}.
 * This indirection avoids platform-specific launch issues when starting JavaFX apps.
 */
public class Launcher {

    /**
     * Entry point that sets JavaFX system properties and forwards to {@link Main#main(String[])}.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        System.setProperty("javafx.platform", "gtk");
        Main.main(args);
    }
}
