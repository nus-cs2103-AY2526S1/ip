package lynx.ui;

/**
 * Class containing general methods pertaining to the UI.
 */
public abstract class LynxUI {

    /**
     * Prints a line.
     */
    private static final String LINE = "____________________________________________________________";

    public static void line() {
        System.out.println(LINE);
    }

    /**
     * Prints a line before and after a message.
     *
     * @param message Message to be printed.
     */
    public static void printBox(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints a greeting.
     */
    public static void hello() {
        LynxUI.printBox("Hello! I'm Tasklynx. \n" +
                "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
    }

    /**
     * Prints a farewell.
     */
    public static void bye() {
        LynxUI.printBox("Goodbye. I'll be here whenever you need to stay on track.");
    }

}
