package tweety.ui;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles user interface operations for the Tweety application.
 * Manages input reading and output formatting with consistent visual styling.
 */
public class Ui {
    private static final String FORMATTING_GAP_DEFAULT = "     ";
    private static final String FORMATTING_LINE = FORMATTING_GAP_DEFAULT
            + "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance.
     * Initialises the scanner for reading user input from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from user input.
     * Waits for user to enter a line of text and returns it.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message when the application starts.
     * Shows a greeting and prompts the user for input with formatted borders.
     */
    public void printWelcomeMessage() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Hello! I'm Tweety");
        System.out.println(FORMATTING_GAP_DEFAULT + "What can I do for you?");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays the exit message when the application terminates.
     * Shows a goodbye message with formatted borders.
     */
    public void printExitMessage() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Bye. Hope to see you again soon!");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays all tasks in the task list with numbered formatting.
     * Shows each task with its task number and description within formatted borders.
     *
     * @param tasks The TaskList containing all tasks to display.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            System.out.println(FORMATTING_GAP_DEFAULT + (i + 1) + ". " + currTask);
        }
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays message when taskList is empty
     */
    public void printNoTaskLeft() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Nice! You don't have any tasks due at the moment.");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays confirmation message for a newly added task.
     * Shows the added task details and updated task count with formatted borders.
     *
     * @param addedTask The task that was added to the list.
     * @param taskCount The total number of tasks after adding.
     */
    public void printAddedTask(Task addedTask, int taskCount) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Got it. I've added this task:");
        System.out.println("          " + addedTask);
        System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + taskCount
                + " tasks in the list");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays confirmation message for a deleted task.
     * Shows the deleted task details and updated task count with formatted borders.
     *
     * @param deletedTask The task that was removed from the list.
     * @param taskCount The total number of tasks after deletion.
     */
    public void printDeletedTask(Task deletedTask, int taskCount) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays confirmation message for an unmarked task.
     * Shows the task that was marked as not done with formatted borders.
     *
     * @param unmarkedTask The task that was unmarked.
     */
    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "OK, I've marked this task as not done yet:");
        System.out.println("          " + unmarkedTask);
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays confirmation message for a marked task.
     * Shows the task that was marked as done with formatted borders.
     *
     * @param markedTask The task that was marked as completed.
     */
    public void printMarkedTask(Task markedTask) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Nice! I've marked this task as done:");
        System.out.println("          " + markedTask);
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays matching tasks from a search or filter operation.
     * Shows each matching task with its task number and description within formatted borders.
     *
     * @param tasks The TaskList containing matching tasks to display.
     */
    public void printFindTask(TaskList tasks) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            System.out.println(FORMATTING_GAP_DEFAULT + (i + 1) + ". " + currTask);
        }
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays confirmation message for a edited task.
     * Shows the task that was edited with formatted borders.
     *
     * @param editedTask The task that was edited.
     */
    public void printEditedTask(Task editedTask) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Nice! I've edited this task:");
        System.out.println("          " + editedTask);
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays an error message in a formatted manner.
     * Shows the exception message within formatted borders for consistent error presentation.
     *
     * @param e The exception whose message should be displayed.
     */
    public void printErrorMessage(Exception e) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + e.getMessage());
        System.out.println(FORMATTING_LINE);
    }


    /**
     * Closes the scanner to free system resources.
     * Should be called when the application terminates to properly clean up resources.
     */
    public void closeScanner() {
        scanner.close();
    }
}
