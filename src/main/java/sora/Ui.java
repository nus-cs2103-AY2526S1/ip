package sora;

import java.util.ArrayList;
import java.util.Scanner;

import sora.list.TaskList;
import sora.task.Task;

/**
 * The {@code Ui} class handles all interactions with the user.
 * It is responsible for displaying messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcome() {
        String logo = "  ____\n"
                + " / ___|  ____ ______ __\n"
                + " \\___ \\ / __ \\| '__/ _  \\\n"
                + "  ___\\ | |__| | | | |_| |\n"
                + " |____/ \\____/|_|  \\___/|\n";
        return "Hello from\n" + logo + "Hello! I'm Sora\n" + "What can I do for you?\n";
    }

    /**
     *  Displays the goodbye message when the application terminates.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display.
     */
    public String showError(String message) {
        assert message != null : "Error message must not be null";
        return "Oh no! " + message + "\n";
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task the added task.
     * @param size the size of the task list after the task is added.
     */
    public String showAddedTask(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the deleted task.
     * @param size the size of the task list after the task is deleted.
     */
    public String showDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have "
                + size + " tasks in the list.\n";
    }

    /**
     * Displays the current task list.
     *
     * @param tasks the current task list.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "TaskList must not be null";
        StringBuilder output = new StringBuilder("Here are the task in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(".").append(tasks.getTask(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task which is marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task the task which is marked as not done.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Displays the list of tasks that match a user's search keyword.
     * <p>
     * If the list of matching tasks is empty, a message will be shown to indicate
     * that no tasks were found. Otherwise, the tasks are printed in order with
     * their corresponding index numbers starting from 1.
     * </p>
     *
     * @param tasks the list of tasks that matched the search keyword.
     */
    public String showFoundTask(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Oh no, there is not any task with this keyword in the list\n";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            return output.toString();
        }
    }

    /**
     * Reads a command from the user. If there is none, returns "bye"
     * for the application to terminate
     *
     * @return the full user input as a string.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "bye";
        }
    }
}
