package beebong.ui;

/**
 * Handles the display of messages and UI-related output for the BeeBong chatbot.
 */
public class UI {

    /**
     * Prints out the message border line for the chatbot messages.
     */
    public void printBorder() {
        String BORDER = "____________________________________________________________";
        System.out.println(BORDER);
    }

    /**
     * Prints out text formatted as a chatbot message.
     *
     * @param message the text to display
     */
    public void showMessage(String message) {
        printBorder();
        System.out.println(message);
        printBorder();
    }

    /**
     * Prints out string formatted as a chatbot error message.
     *
     * @param errorMessage the text to display
     */
    public void showErrorMessage(String errorMessage) {
        showMessage("Bong Alert! - " + errorMessage);
    }

    /**
     * Prints out the chatbot's greeting message.
     */
    public void showGreetingMessage() {
        showMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    /**
     * Prints out the chatbot's exit message.
     */
    public void showExitMessage() {
        showMessage("Ding ding! Time to go. See you soon!");
    }

    /**
     * Prints out the list of available chatbot commands and usage instructions.
     */
    public void showCommands() {
        String commandList = """
                    List - lists out all tasks
                    Find [keyword] - lists out all tasks whose name contains the keyword
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Delete [task no.] - removes a task from the list
                    Help - shows full command list
                    Bye - exit
                    (Enter Dates using this format: DD/MM/YYYY hh:mm, time is optional)
                    Enter a new Task name or Command""";
        showMessage(commandList);
    }
}
