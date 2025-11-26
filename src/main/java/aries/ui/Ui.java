package aries.ui;

import java.util.Scanner;

import aries.task.Task;
import aries.task.TaskList;

/**
 * Ui class handles all interactions with the user.
 * It reads user input and displays messages to the user.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user with a welcome message.
     */
    public String showWelcomeMessage() {
        return ("Hello! I'm Aries.\n" + "What can I do for you?");
    }

    /**
     * Bids farewell to the user and closes the scanner.
     */
    public String showExitMessage() {
        closeScanner();
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input entered by the user.
     */
    public String readInputString() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR).append("\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        sb.append(SEPARATOR);
        return sb.toString();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param tasks The TaskList after addition.
     */
    public String showAddedString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(tasks.get(tasks.size() - 1)).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task  The Task that was deleted.
     * @param tasks The TaskList after deletion.
     */
    public String showDeletedString(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done or not done.
     *
     * @param task   The Task to be marked.
     * @param isDone A boolean indicating whether the task is marked as done (true) or not done (false).
     */
    public String showMarkedStatus(Task task, boolean isDone) {
        StringBuilder sb = new StringBuilder();
        sb.append(isDone ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:").append("\n");
        sb.append(task).append("\n");
        return sb.toString();
    }

    /**
     * Displays the tasks found based on a search query.
     *
     * @param foundTasks The TaskList containing the found tasks.
     */
    public String showFoundTasks(TaskList foundTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR).append("\n");

        if (foundTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:" + "\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                sb.append((i + 1) + ". " + foundTasks.get(i) + "\n");
            }
        }

        sb.append(SEPARATOR);
        return sb.toString();
    }

    /**
     * Displays the list of available commands to the user.
     *
     * @return A string listing all available commands.
     */
    public String showAvailableCommands() {
        return String.join("\n",
            "list      : Lists all previously added tasks.",
            "todo      : Add a todo task.",
            "deadline  : Add a task with a specific time.",
            "event     : Add an event with a time range.",
            "find      : Find tasks containing a keyword.",
            "mark      : Mark a task as done.",
            "unmark    : Mark a task as not done.",
            "delete    : Delete a task by its index.",
            "help      : Show available commands.",
            "bye       : Exit the program."
        );
    }
}
