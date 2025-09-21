package merlie.ui;

import java.util.List;
import java.util.Scanner;

import merlie.task.Task;
import merlie.tasklist.TaskList;

/**
 * Handles all user interactions, such as displaying messages, task updates, and reading input.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints a welcome message when the application starts.
     */
    public void start() {
        //return "\nHello! I'm Merlie the Merlione" + printLine() + "What can I do for you?" + printLine()
        System.out.print("\n");
        printLine();
        output("Hello! I'm Merlie the Merlion");
        output("What can I do for you?");
        printLine();
    }

    public String getStartForGui() {
        return "Hello! I'm Merlie the Merlion\nWhat can I do for you?";
    }

    /**
     * Prints a horizontal line separator.
     */
    public void printLine() {
        System.out.println("_____________________________________________________________________________"
                + "______________________");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The trimmed user input as a String.
     */
    public String readInput() {
        System.out.print("You: ");
        return sc.nextLine().trim();
    }

    /**
     * Prints a goodbye message and closes the input scanner.
     */
    public void end() {
        output("Hope to see you again soon! Majulah Singapura!");
        sc.close();
    }

    /**
     * Displays the current number of tasks in the task list.
     *
     * @param size The number of tasks.
     */
    public void taskSizeOutput(int size) {
        output("You have " + size + " tasks on your list!");
    }

    /**
     * Prints all tasks in the provided TaskList.
     *
     * @param list The TaskList containing tasks to display.
     */
    public void showList(TaskList list) {
        output("Here’s what you’ve got:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.getTask(i));
        }
    }

    /**
     * Prints all matching tasks in the provided list of tasks.
     *
     * @param list The list of tasks containing all matches to display.
     */
    public void showMatches(List<Task> list) {
        output("Ooo, found the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Prints a message for a task marked as done.
     *
     * @param task The Task that was completed.
     */
    public void markDoneOutput(Task task) {
        output("Congratulations on completing your task:");
        System.out.println("     " + task);
    }

    /**
     * Prints a message for a task marked as not done.
     *
     * @param task The Task that was unmarked.
     */
    public void markUndoneOutput(Task task) {
        output("Rawrr, I've marked this task as not done (YET):");
        System.out.println("     " + task);
    }

    /**
     * Prints a message for a task has been updated.
     *
     * @param task The Task that was updated.
     */
    public void updateOutput(Task task) {
        output("Ooo, updated the task for: " + task);
    }

    /**
     * Prints a message for a new task that has been added to the task list.
     *
     * @param task The Task that was added.
     * @param size The total number of tasks after addition.
     */
    public void addOutput(Task task, int size) {
        output("Got it! I've added this to your task list:\n\t" + task);
        output("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message for a task that has been deleted from the task list.
     *
     * @param task The Task that was deleted.
     * @param size The total number of tasks after deletion.
     */
    public void deleteOutput(Task task, int size) {
        output("Okie, I’ve sprayed water on the task, so it has EXTINGUISHED…:");
        System.out.println("     " + task);
        output("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints an error message to the user.
     *
     * @param error The error message to display.
     */
    public void errorOutput(String error) {
        output("Rawrr, " + error);
    }

    /**
     * Echoes a message back to the user when a non-existent command is entered
     *
     * @param message The message to echo.
     */
    public void echo(String message) {
        output("Echo after you AH!: " + message);
    }

    public String formatForGui(String rawOutput) {
        return rawOutput.replaceAll("Merlie: ", "").trim();
    }

    /**
     * Prints a message prefixed with "Merlie: ".
     *
     * @param message The message to display.
     */
    private void output(String message) {
        System.out.println("Merlie: " + message);
    }
}
