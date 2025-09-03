package bytebot.ui;

import java.util.List;
import java.util.Scanner;

import bytebot.task.Task;

/**
 * Handles all user-facing input and output
 */
public class Ui {
    private final String line = "____________________________________________________________\n";
    private final Scanner scanner;

    /**
     * Creates a UI instance using standard input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the greeting banner.
     */
    public void showGreeting() {
        System.out.println(line
                + "Hello! I'm Byte.\nWhat can I do for you?\n"
                + line);
    }

    /**
     * Shows the farewell banner.
     */
    public void showFarewell() {
        System.out.println("\t" + line
                + "\t" + "Bye, hope to see you again soon!\n"
                + "\t" + line);
    }

    /**
     * Shows an error message.
     *
     * @param message Error to display
     */
    public void showError(String message) {
        System.out.println("\t" + line
                + "\t" + message + "\n"
                + "\t" + line);
    }

    /**
     * Displays a numbered list of tasks.
     *
     * @param tasks Tasks to display
     */
    public void showTasks(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n\t").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
        }
        System.out.println("\t" + line
                + "\t" + output + "\n"
                + "\t" + line);
    }

    /**
     * Displays tasks that match a search term.
     *
     * @param tasks Tasks matching the search
     */
    public void showMatching(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n\t").append(i + 1).append(".")
                  .append(tasks.get(i).toString());
        }
        System.out.println("\t" + line
                + "\t" + output + "\n"
                + "\t" + line);
    }

    /**
     * Shows news after a task is added.
     *
     * @param task The added task
     * @param total Total number of tasks after addition
     */
    public void showAddedTask(Task task, int total) {
        System.out.println(
                "\t" + line
                + "\t" + "Got it, I've added this task:\n\t  " + task
                + "\n\tNow you have " + total + " tasks in the list."
                + "\n" + "\t" + line
        );
    }

    /**
     * Shows news after a task is marked as done.
     *
     * @param task The task that was marked done
     */
    public void showMarked(Task task) {
        System.out.println(
                "\t" + line
                + "\t" + "Nice! I've marked this task as done:\n\t  " + task
                + "\n" + "\t" + line
        );
    }

    /**
     * Shows news after a task is unmarked.
     *
     * @param task The task that was unmarked
     */
    public void showUnmarked(Task task) {
        System.out.println(
                "\t" + line
                + "\t" + "OK, I've marked this task as not done yet:\n\t  " + task
                + "\n" + "\t" + line
        );
    }

    /**
     * Shows news after a task is deleted.
     *
     * @param removed The removed task
     * @param total Total number of tasks after deletion
     */
    public void showDeleted(Task removed, int total) {
        System.out.println(
                "\t" + line
                + "\t" + "I have removed this task:\n\t  " + removed
                + "\n\tNow you have " + total + " tasks in the list."
                + "\n" + "\t" + line
        );
    }

    /**
     * Reads the next line of user input.
     *
     * @return The input string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Close scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}


