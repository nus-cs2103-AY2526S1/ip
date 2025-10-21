package UI;

import Task.TaskList;
import Task.TaskItem;

import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Reads a line of user input from the input stream.
     *
     * @return the raw user command string (without trailing newline)
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints an error message in a consistent UI style.
     *
     * @param msg error text to show
     */
        public void showError(String msg) {
            System.out.println(msg + "\nPlease try again.");
        }

    /**
     * Prints the welcome banner and any initial help text.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm John.John");
        System.out.println("How may I assist you?");
    }

    /**
     * Displays the goodbye message when the application is closed.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message confirming that a task has been added.
     *
     * @param task the task added to the list.
     * @param size the current number of tasks.
     */
    public void showAdded(TaskItem task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in this list");
    }

    /**
     * Displays the current tasks in the list.
     *
     * @param tasks list of current tasks.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getItem(i));
        }
    }

    /**
     * Displays the message confirming that a task has been marked.
     *
     * @param task task that has been marked as done.
     */
    public void showMarked(TaskItem task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message confirming that a task has been unmarked.
     *
     * @param task task that has been marked as undone.
     */
    public void showUnmarked(TaskItem task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message confirming that a task has been deleted.
     *
     * @param task task that has been deleted.
     * @param size the current number of tasks.
     */
    public void showDeleted(TaskItem task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Prints a horizontal rule or divider line to separate sections.
     */
    public static void showLine() {
        System.out.println("===================================================");
    }
}
