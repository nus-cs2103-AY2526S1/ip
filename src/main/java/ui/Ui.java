package ui;

import java.util.Scanner;

import exception.RainyException;
import tasks.TaskList;

/**
 * Handles all user interactions for the Rainy application.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new {@code Ui} with a {@link Scanner} for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return the full command entered by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public String showWelcome() {
        return "hi! i'm rainy! :D\nwhat can i do for u?\n";
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public String showBye() {
        return "bai bai! see u again >_<!\n";
    }

    /**
     * Displays a horizontal line divider.
     */
    public String showLine() {
        return "__________________________________\n";
    }

    /**
     * Displays an error message wrapped with divider lines.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(showLine() + message + "\n" + showLine());
    }

    /**
     * Returns a formatted string representation of all tasks in the given {@link TaskList}.
     * <p>
     * Each task is displayed on a new line, preceded by its index in the list (starting from 1).
     * The output is intended to provide the user with a clear overview of their current tasks.
     * </p>
     *
     * @param tasks the {@code TaskList} containing the tasks to be displayed
     * @return a formatted string listing all tasks with their indices
     */
    public String showList(TaskList tasks) throws RainyException {
        StringBuilder sb = new StringBuilder();
        sb.append("oki! here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Closes the {@link Scanner} used for reading input.
     */
    public void close() {
        sc.close();
    }
}
