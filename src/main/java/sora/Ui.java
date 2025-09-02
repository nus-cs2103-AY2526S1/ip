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
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        String logo = "  ____\n"
                + " / ___|  ____ ______ __\n"
                + " \\___ \\ / __ \\| '__/ _  \\\n"
                + "  ___\\ | |__| | | | |_| |\n"
                + " |____/ \\____/|_|  \\___/|\n";
        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("Hello! I'm Sora");
        System.out.println("What can I do for you?");
    }

    /**
     *  Displays the goodbye message when the application terminates.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println("Oh no! " + message);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task the added task.
     * @param size the size of the task list after the task is added.
     */
    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the deleted task.
     * @param size the size of the task list after the task is deleted.
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the current task list.
     *
     * @param tasks the current task list.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task which is marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task the task which is marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
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
    public void showFoundTask(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Oh no, there is not any task with this keyword in the list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
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
