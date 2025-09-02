package paul.ui;

import paul.task.Task;
import paul.task.TaskList;

import java.util.Scanner;

/**
 * Handles user interaction for Paul.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;

    // Scanner to read user input
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the output with a line surrounding it.
     *
     * @param output The message to print.
     */
    public void printOutput(String output) {
        System.out.println(LINE);
        System.out.println(output);
        System.out.println(LINE);
    }

    /**
     * Reads a line of input from the user, removing any whitespace.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Greets user with a welcome message and logo.
     */
    public void greetUser() {
        printOutput("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user before exiting the program.
     */
    public void byeUser() {
        printOutput("Goodbye! Paul will miss you :(");
    }

    /**
     * Displays all the tasks currently in the TaskList.
     *
     * @param tasks The TaskList to display.
     */
    public void showTasks(TaskList tasks) {
        printOutput("Here are the tasks in your list:\n" + tasks);
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task to add.
     * @param total The total number of tasks after adding.
     */
    public void showTaskAdded(Task task, int total) {
        printOutput("Got it. I've added this task:\n" + task +
                "\nNow you have " + total + " tasks in the list.");
    }

    /**
     * Shows a message when a task is marked.
     *
     * @param task The task to mark.
     */
    public void showTaskMarked(Task task) {
        printOutput("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Shows a message when a task is unmarked.
     *
     * @param task The task to unmark.
     */
    public void showTaskUnmarked(Task task) {
        printOutput("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task The task to delete.
     * @param total The total number of tasks after deleting.
     */
    public void showTaskDeleted(Task task, int total) {
        printOutput("Noted. I've removed this task:\n" + task +
                "\nNow you have " + total + " tasks in the list.");
    }

    /**
     * Shows a message with the all tasks found using the find method.
     *
     * @param list The TaskList containing the tasks found.
     */
    public void showTaskFound(TaskList list) {
        if (list.size() == 0) {
            printOutput("There are no matches found!");
        } else {
            printOutput("Here are the matching tasks in your list:\n" + list);
        }
    }

    /**
     * Shows a message when there is an error loading tasks.
     */
    public void showLoadingError() {
        printOutput("Error in Loading Tasks!");
    }

    /**
     * Show a custom message when there is an error.
     *
     * @param message The message to print.
     */
    public void showError(String message) {
        printOutput(message);
    }
}
