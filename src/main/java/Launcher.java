import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        if (java.awt.GraphicsEnvironment.isHeadless()) {
            System.out.println("Melody Task Manager");
            System.out.println("======================");
            System.out.println("This application requires a graphical display.");
            System.out.println("");
            System.out.println("To run on this system, please:");
            System.out.println("   1. Install graphics support:");
            System.out.println("      Ubuntu: sudo apt-get install libgl1-mesa-dev");
            System.out.println("   2. Or use software rendering:");
            System.out.println("      java -Dprism.order=sw -jar melody.jar");
            System.out.println("");
            System.out.println("For help, contact your system administrator.");
            System.exit(0);
        } else {
            // Normal GUI mode
            Application.launch(Main.class, args);
        }
    }


}
