package steven.ui;

/**
 * Handles the user interface and interactions for the Steven chatbot.
 * Provides methods to display greetings, separators, and exit messages.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String GREETING = "Hello There! I'm Steven\nHow may I help you?";

    /**
     * Prints a horizontal line separator.
     */
    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        System.out.println(GREETING);
    }

    public String getGreeting() {
        return GREETING;
    }

    public void print(String text) {
        System.out.println(text);
    }
}
