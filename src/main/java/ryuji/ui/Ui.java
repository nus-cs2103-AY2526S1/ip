package ryuji.ui;

import ryuji.task.TaskList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user.
 * <p>This class is responsible for displaying messages to the user, reading input from the user, and showing outputs related to tasks such as the task list, errors, and greetings.</p>
 */
public class Ui {

    /**
     * ASCII art logo for the application, displayed on startup.
     */
    private static final String LOGO = " ____               _ _ \n"
            + "|  _ \\ _   _ _   _ (_|_)\n"
            + "| |_) | | | | | | || | |\n"
            + "|  _ <| |_| | |_| || | |\n"
            + "|_| \\_\\\\__, |\\__,_|/ |_|\n"
            + "       |___/     |__/   \n";

    /**
     * A horizontal divider line used for formatting console output.
     */
    private static final String LINE = "_______________________________________________________";

    /**
     * Default greeting message shown when the application starts.
     */
    private static final String GREETING = "Hello! I'm Ryuji.\nWhat can I do for you?";

    /**
     * Farewell message displayed when the application is closed or exited.
     */
    private static final String FAREWELL = "Bye. Hope to see you again soon!\n";

    /**
     * Scanner used to read user input from the standard input stream (console).
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message and logo when the application starts.
     *
     * @return the formatted welcome message with the logo
     */
    public static String showWelcome() {
        return LINE + "\n" + LOGO + "\n" + GREETING + "\n" + LINE;
    }

    /**
     * Displays a horizontal divider line to separate console output.
     *
     * @return a string containing the divider line
     */
    public String showLine() {
        return this.LINE;
    }

    /**
     * Displays a farewell message when the user exits the application.
     *
     * @return the farewell message
     */
    public String showFarewell() {
        return this.FAREWELL;
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks the {@code TaskList} containing all tasks to be displayed
     * @return a string representation of the task list
     */
    public String displayTaskList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return the user input as a {@code String}
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param exception the error message to be shown
     * @return the error message wrapped in a {@code RyujiException}
     */
    public String showError(String exception) {
        return new RyujiException(exception).getMessage();
    }

    /**
     * Displays an error message when the task list fails to load from file.
     * An empty list will be initialized in this case.
     *
     * @return the error message indicating task list loading failure
     */
    public String showFailuretoLoadTaskListError() {
        return "The file you have given me does not exist master, here's an empty list for this session";
    }
}
