package app;

import commands.Command;
import errors.BoopError;

/**
 * The Boop class serves as the main application controller
 * that manages user input, task storage, and message handling.
 *
 * It uses a Parser to interpret user input into commands,
 * a TaskList to maintain the list of tasks, and a
 * MessageHandler to generate user-facing messages.
 */
public class Boop {
    private MessageHandler messageHandler;
    private TaskList taskList;
    private Parser parser;

    /**
     * A response wrapper that encapsulates a message to the user
     * and a flag indicating whether the application should exit.
     */
    public class BoopResponse {
        private final String message;
        private final boolean isExit;

        /**
         * Constructs a BoopResponse.
         *
         * @param message the message to return to the user
         * @param isExit  true if the program should exit, false otherwise
         */
        public BoopResponse(String message, boolean isExit) {
            this.message = message;
            this.isExit = isExit;
        }

        /**
         * Returns the message associated with this response.
         *
         * @return the message string
         */
        public String getMessage() {
            return message;
        }

        /**
         * Indicates whether this response signals the program to exit.
         *
         * @return true if the response signals an exit, false otherwise
         */
        public Boolean isExit() {
            return isExit;
        }
    }

    /**
     * Creates a new Boop instance with the default task storage path.
     * The default path is ./data/tasks.txt.
     */
    public Boop() {
        this("./data/tasks.txt");
    }

    /**
     * Creates a new Boop instance with a specified task storage path.
     *
     * @param taskSavePathName the path to the file where tasks are stored
     */
    public Boop(String taskSavePathName) {
        messageHandler = new MessageHandler();
        taskList = new TaskList(taskSavePathName);
        parser = new Parser();
    }

    /**
     * Initializes the application and returns the greeting message.
     *
     * @return a greeting string to display to the user
     */
    public String initialize() {
        return messageHandler.greeting();
    }

    /**
     * Loads tasks from the storage file into the task list.
     *
     * @return a confirmation message if successful,
     *         or an error message if a BoopError occurs
     */
    public String loadTasks() {
        try {
            taskList.loadTasks();
        } catch (BoopError e) {
            return messageHandler.errorMessage(e);
        }

        return messageHandler.finishLoading();
    }

    /**
     * Processes the user input string, executes the corresponding command,
     * and returns a BoopResponse containing the result.
     *
     * @param input the raw user input string
     * @return a BoopResponse containing the message to display and whether the
     *         program should exit
     */
    public BoopResponse getResponse(String input) {
        try {
            Command c = parser.getNextCommand(input);

            c.execute(taskList);

            return new BoopResponse(c.getMessage(), c.isExit());
        } catch (BoopError e) {
            return new BoopResponse(messageHandler.errorMessage(e), false);
        }
    }
}
