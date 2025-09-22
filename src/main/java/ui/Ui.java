package ui;

/**
 * A utility class for reading user input and displaying program output.
 */
public class Ui {
    private final StringBuilder sb = new StringBuilder();

    public Ui() {}

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        chatbotPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an (error) message with an "Error:" header.
     * @param msg the error message
     */
    public void showError(String msg) {
        chatbotPrint("Error: " + msg);
    }

    /**
     * Adds the input string to the current chatbot string stack, for the GUI to display at a later point.
     *
     *<p>The StringBuilder is used here so that methods that print multiple lines can have all of their respective
     * print lines collated before being sent back to the GUI when it calls for a response.</p>
     * @param s the String to be printed
     */
    public void chatbotPrint(String s) {
        sb.append(s).append("\n");
    }

    /**
     * Returns the current string stored in the {@link StringBuilder} {@code sb} and clears it for the next string
     * to be built.
     *
     * @return the string stored in the {@code sb}.
     */
    public String printToGui() {
        String res = sb.toString();
        sb.setLength(0);
        return res;
    }

}
