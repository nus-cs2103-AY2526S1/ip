package noob.ui;

import noob.exception.NoobException;

/**
 * Class responsible for message responses to user input
 */
public class Ui {
    private String LINE_SPACING = "     ";

    /**
     * Indents bot replies
     *
     * @param text Text to be indented
     * @return The input string
     */
    private String indentedReply(String text) {
        String[] lineSplit = text.split("\n");
        for (String s : lineSplit) {
            System.out.println(LINE_SPACING + s);
        }
        return text;
    }

    /**
     * Displays error message on initialization
     *
     * @param e NoobException thrown on initialization of the bot
     */
    public void displayInitializationError(NoobException e) {
        System.out.println(e.getMessage() + "\n");
    }

    /**
     * Displays general error messages
     *
     * @param e NoobException thrown by commands
     * @return Error message
     */
    public String displayError(NoobException e) {
        return indentedReply(e.getMessage());
    }

    /**
     * Displays bot messages or replies to user input
     *
     * @param msg Message to be displayed
     * @return Message
     */
    public String displayMessage(String msg) {
        return indentedReply(msg);
    }

    /**
     * Greets the user upon bot start up
     *
     * @return Greeting message
     */
    public String greet() {
        String msg = "Hello! I'm Noob\nWhat can I do for you?";
        System.out.println(msg);
        return msg;
    }

    /**
     * Exits conversation with a message
     *
     * @return Exit message
     */
    public String exit() {
        String msg = "Bye. Hope to see you again soon!";
        System.out.println(msg);
        return msg;
    }
}
