/**
 * Handles all interactions with the user.
 * Responsible for displaying greetings, prompts,
 * and error messages in a consistent style.
 */

package salah;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Handles all interactions with the user.
 * Responsible for displaying greetings, prompts, and error messages
 * in a consistent style.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} that reads from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the greeting
     */
    public void showGreeting() {
        System.out.println("Hi, I'm Mohammed Salah, The Egyptian King");
        System.out.println("How may I assist you today?");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the raw command line entered by the user (never {@code null} unless input is closed)
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Displays an error message wrapped with divider lines.
     *
     * @param message the error message to show
     */
    public void showError(String message) {
        showLine();
        System.out.println("Error: " + message);
        showLine();
    }

    /**
     * Displays the farewell message.
     */
    public void showBye() {
        showLine();
        System.out.println("Hope you have a great day! Remember, you'll never walk alone :))");
        showLine();
        try {
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.exit(0); 
    }

    /**
     * Checks if tasks are empty and displays them if not
     *
     * @param tasks the tasks to display
     */
    public void showList(List<Task> tasks) {
        // Assumption: showList should never be called with an empty list
        assert tasks != null : "Task list must not be null";
        assert !tasks.isEmpty() : "Task list must not be empty when calling showList";

        showLine();
        displayList(tasks);
        showLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks the tasks to display
     */
    public void displayList(List<Task> tasks) {
        System.out.println("The Egyptian King has these tasks for you to complete:");
        IntStream.range(0, tasks.size())
             .forEach(i -> System.out.println((i + 1) + ". " + tasks.get(i)));
    }

    /**
     * Shows a confirmation that a task was added.
     *
     * @param task  the task that was added
     * @param total the total number of tasks after the addition
     */
    public void showAdded(Task task, int total) {
        showLine();
        System.out.println("added: " + task.toString());
        System.out.println("The Egyptian King detects " + total + " tasks in your list!");
        showLine();
    }

    /**
     * Shows a confirmation that a task was removed.
     *
     * @param task      the task that was removed
     * @param remaining the total number of tasks remaining
     */
    public void showDeleted(Task task, int remaining) {
        showLine();
        System.out.println("The Egyptian king has removed this task:");
        System.out.println(task.toString());
        System.out.println("There are still " + remaining + " tasks in your list!");
        showLine();
    }

    /**
     * Shows a confirmation that a task was marked complete.
     *
     * @param task the task marked as complete
     */
    public void showMarked(Task task) {
        showLine();
        System.out.println("This task is marked as complete. Well done!");
        System.out.println(task.toString());
        showLine();
    }

    /**
     * Shows a confirmation that a task was marked incomplete.
     *
     * @param task the task marked as incomplete
     */
    public void showUnmarked(Task task) {
        showLine();
        System.out.println("Task has been marked as incomplete.");
        System.out.println(task.toString());
        showLine();
    }

    /**
     * Checks if description contains the given keyword for any given task.
     *
     * @param keyword the keyword to search for
     * @param matches list of tasks matching the keyword
     */
    public void showFindResults(String keyword, List<Task> matches) {
        // Assumption: keyword must not be null or empty
        assert keyword != null : "Search keyword must not be null";
        assert !keyword.trim().isEmpty() : "Search keyword must not be empty";

        showLine();
        if (matches.isEmpty()) {
            System.out.println("No tasks found matching: " + keyword);
        } else {
            showMatches(keyword, matches);
        }
        showLine();
    }

    /**
     * Displays tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for
     * @param matches list of tasks matching the keyword
     */
    public void showMatches(String keyword, List<Task> matches) {
        System.out.println("Here are the matching tasks with keyword \"" + keyword + "\":");
        IntStream.range(0, matches.size())
             .forEach(i -> System.out.println((i + 1) + ". " + matches.get(i)));
    }

    /** Close resources when app exits. */
    public void close() {
        scanner.close();
    }
}
