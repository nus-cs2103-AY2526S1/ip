package chirp.io;

import chirp.tasks.TaskList;

/**
 * Manages input and output with the user
 */
public class Ui {
    private static final String NAME = "Chirp";
    private MainWindow window;

    /**
     * Creates Ui Object
     *
     * @param window Display window
     */
    public Ui(MainWindow window) {
        this.window = window;
    }

    /**
     * Displays a message to the user
     *
     * @param messages Lines of messages to display
     */
    public void printMessage(String... messages) {
        StringBuilder response = new StringBuilder();
        for (String message : messages) {
            response.append(message + '\n');
        }
        window.sendChirpMessage(response.toString());
    }

    /**
     * Informs the user that a unrecoverable error has occurred and the chat is terminated
     *
     * @param error The error message
     */
    public void fatalError(String error) {
        printMessage(" FATAL: ", error, " Shutting down...");
    }

    /**
     * Informs the user that an error has occurred during the loading of the data file
     * and the task list is defaulted to being empty
     *
     * @param error The error message
     */
    public void loadingError(String error) {
        printMessage(" Error in loading: ", error, " Defaulting to empty task list.");
    }

    /**
     * Informs the user that an error has occurred due to invalid input
     *
     * @param error The error message
     */
    public void inputError(String error) {
        printMessage(" Invalid Input: ", error, " Please try again!");
    }

    /**
     * Helps to print greeting message
     */
    public void greet() {
        printMessage(" Hello! I'm " + NAME, " What can I do for you?");
    }

    /**
     * Helps to print farewell message
     */
    public void exit() {
        printMessage(" Bye. Hope to see you again soon!");
    }

    /**
     * Helps to return info string on number of tasks in tasklist
     *
     * @param taskList
     * @return Info string
     */
    public String taskListCount(TaskList taskList) {
        return " Currently " + taskList.getNumOfTasks() + " tasks in the task list.";
    }

    /**
     * Helps to print query message for tasks data file path
     * @param defaultFilePath
     */
    public void queryFilePath(String defaultFilePath) {
        printMessage("Hi! First enter tasks data file path",
                "(Enter empty string to default to " + defaultFilePath + ")");
    }
}
