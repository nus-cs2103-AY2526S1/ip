package heimerdinger;

import java.util.Scanner;

import heimerdinger.task.Task;
import heimerdinger.task.TaskList;

/**
 * Handles the output to be shown to user for all input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a Ui instance with a scanner that reads input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message when Heimerdinger application is run.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Heimerdinger.Heimerdinger. What can I do for you?\n");
    }

    /**
     * Reads next line of user input.
     * @return raw string provided by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Stylistic choice for readability, produces a separating line.
     */
    public void showLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Displays the error message.
     */
    public void showError(String message) {
        System.out.println("Uhoh! " + message);
    }

    /**
     * Displays an error when task file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error loading saved files. Starting with new list");
    }

    /**
     * Displays confirmation text when task is added to list.
     */
    public void showAdded(Task t) {
        System.out.println("Noted. Added task:");
        System.out.println("    " + t);
    }

    /**
     * Displays confirmation text when task is removed from list.
     *
     * @param t Task to be deleted from list
     * @param size size of subsequent list
     */
    public void showDeleted(Task t, int size) {
        System.out.println("Noted. Deleted task:");
        System.out.println("    " + t);
        System.out.println(size + " item(s) in your inventory now.");
    }

    /**
     * Displays confirmation text when task is marked as done.
     *
     * @param t Task to be marked as done
     * @param tasks TaskList of all tasks
     */
    public void showMarked(Task t, TaskList tasks) {
        System.out.println("Indeed, a wise choice! Here's your list:");
        showTasks(tasks);
    }

    /**
     * Displays confirmation text when task is marked as not done.
     *
     * @param t Task to be marked as not done
     * @param tasks TaskList of all tasks
     */
    public void showUnmarked(Task t, TaskList tasks) {
        System.out.println("One step forward, two steps back. Here's your list:");
        showTasks(tasks);
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param tasks the filtered task list
     * @param word  the keyword used for matching
     */
    public void showFoundTasks(TaskList tasks, String word) {
        System.out.println("Here are the task items that match your target word '" + word + "':");
        showTasks(tasks);
    }

    /**
     * Displays tasks scheduled on a given date.
     *
     * @param tasks the filtered task list
     * @param word  the date string that was queried
     */
    public void showScheduledTasks(TaskList tasks, String word) {
        System.out.println("You sure are busy, human. Here is your schedule for " + word + ":");
        showTasks(tasks);
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showGoodbye() {
        System.out.println("For Piltover and science! Goodbye!");
    }

    /**
     * Displays a list of all tasks with a header text.
     *
     * @param tasks TaskList of all tasks
     */
    public void showList(TaskList tasks) {
        System.out.println("Roger donger! Here's your list:");
        showTasks(tasks);
    }

    /**
     * Displays a list of all tasks.
     *
     * @param tasks TaskList of all tasks
     */
    public void showTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Nothing in your list for now!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }
}
