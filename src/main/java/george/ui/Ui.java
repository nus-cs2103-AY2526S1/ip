package george.ui;

/**
 * Handles user interface interactions for the George application.
 * Manages displaying messages, greetings, errors, and formatting output.
 */
public class Ui {
    private static final String newLine = """
            GEORGE the monkey LOVES to eat bananas!\
            """;

    private static final String greeting = """
            Ooo eee ooo aaa aaa\s
            I am George the Monkey \n
            George can help you with?""";

    /**
     * Displays the welcome message and introduction when the application starts.
     */
    public void showWelcome() {
        System.out.println(greeting);
        System.out.println(newLine);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display
     */
    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    /**
     * Returns the welcome message for GUI display.
     *
     * @return The formatted welcome message
     */
    public String getWelcomeMessage() {
        return greeting;
    }
}
