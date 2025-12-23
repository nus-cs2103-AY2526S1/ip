package scribbles.ui;

/**
 * Provides the UI elements and printing of Scribbles
 */
public class Ui {

    private static final String WELCOME_MSG = """
            Heya! I'm Scribbles, your personal task assistant!
            I am made out of scribbles! >w<
            What can I do for you? :)
            """;
    private static final String EXIT_MSG = "Bai bai! See you next time! :D";
    private static final String LINE = "    <----------------------------------------------->";

    /**
     * Echoes a list of strings and encapsulate them
     * between horizontal lines.
     *
     * @param lines List of strings to be echoed.
     */
    public static void echo(String... lines) {
        System.out.println(LINE);
        for (String line : lines) {
            System.out.printf("    %s%n", line);
        }
        System.out.println(LINE);
    }

    /**
     * Retrieves welcome message
     *
     * @return the welcome message.
     */
    public static String getWelcomeMsg() {
        return Ui.WELCOME_MSG;
    }

    /**
     * Retrieves exit message
     *
     * @return the exit message.
     */
    public static String getExitMsg() {
        return Ui.EXIT_MSG;
    }

    /**
     * Prints the error message received.
     *
     * @param msg The error message to be printed.
     */
    public static void displayError(String msg) {
        Ui.echo(msg);
    }
}
