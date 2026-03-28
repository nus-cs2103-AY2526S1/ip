package sanbot;

import sanbot.parser.Parser;
import sanbot.storage.Storage;
import sanbot.tasklist.TaskList;
import sanbot.ui.Ui;

/**
 * Main class for the Sanbot chatbot.
 * Handles startup, user input loop, and exit.
 */
public class Sanbot {

    /**
     * Entry point for the Sanbot application.
     * Initializes the chatbot, loads saved tasks, processes user input,
     * and saves tasks before exit.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Ui.greeting();
        Storage.saveTasks(TaskList.getAllTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public static String getResponse(String input) {
        return Parser.handleInput(input);
    }

}