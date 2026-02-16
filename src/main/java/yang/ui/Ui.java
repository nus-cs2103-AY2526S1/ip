package yang.ui;

import java.util.Scanner;

import yang.task.Task;
import yang.task.TaskList;

/**
 * Handles user interactions by printing messages to the console
 * and reading user input.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        System.out.println("Good day! I'm Yang your favourite bot assistant\nWhat can I do for you?");
    }

    /**
     * Reads the next command from user input.
     *
     * @return the trimmed command string
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a confirmation after a task has been added.
     *
     * @param t     the task that was added
     * @param count the total number of tasks after the addition
     */
    public void showAdded(Task t, int count) {
        System.out.println("Perfect! I've added this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tl the task list to display
     */
    public void showList(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.size(); i++) {
            System.out.println((i + 1) + ". " + tl.get(i));
        }
    }

    /**
     * Prints a confirmation after a task has been deleted.
     *
     * @param t     the task that was deleted
     * @param count the total number of tasks after the deletion
     */
    public void showDeleted(Task t, int count) {
        System.out.println("Understood. I've removed this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints a confirmation after a task has been marked as done.
     *
     * @param t the task that was marked as done
     */
    public void showMarked(Task t) {
        System.out.println("Okay! I've marked this task as done:");
        System.out.println("  " + t);
    }

    /**
     * Prints a confirmation after a task has been marked as not done.
     *
     * @param t the task that was unmarked
     */
    public void showUnmarked(Task t) {
        System.out.println("Will do, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    /**
     * Prints tasks that contain a given keyword in their string representation.
     *
     * @param tl      the task list to search
     * @param keyword the substring to match against each task
     */
    public void showFound(TaskList tl, String keyword) {
        System.out.println("I have listed the matching tasks:");
        for (int i = 0; i < tl.size(); i++) {
            Task t = tl.get(i);
            if (t.toString().contains(keyword)) {
                System.out.println((i + 1) + ". " + t);
            }
        }
    }

    /**
     * Prints the given message.
     *
     * @param s the message to print
     */
    public void show(String s) {
        System.out.println(s);
    }
}

