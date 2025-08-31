package sora;

import sora.task.Task;
import sora.list.TaskList;

import java.util.Scanner;

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
        System.out.println("Hello! I'm duke.Sora");
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
     * Reads a command from the user.
     *
     * @return the full user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
