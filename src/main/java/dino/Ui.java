package dino;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface for the application.
 * Returns specific output based on the user command.
 */
public class Ui {
    private static final String LINE = "______________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Displays the welcome message to the user. */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Dino.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Returns the welcome message as a string.
     *
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return LINE + "\nHello! I'm Dino.\nWhat can I do for you?\n" + LINE;
    }

    /** Displays the goodbye message to the user. */
    public void showBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you soon!");
        System.out.println(LINE);
    }

    /**
     * Returns the bye message as a string.
     *
     * @return bye message
     */
    public String getByeMessage() {
        return LINE + "\nBye. Hope to see you soon!\n" + LINE;
    }

    /**
     * Reads the command input from user.
     *
     * @return the line of input entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks the list of tasks
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println(LINE);
    }

    /**
     * Returns the task list in a string format.
     *
     * @param tasks the list of tasks
     * @return ordered task list
     */
    public String getTaskList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\nHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Displays a message that a task has been marked as done.
     *
     * @param task the task to be marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:\n  " + task.toString());
        System.out.println(LINE);
    }

    public String getTaskMarked(Task task) {
        return LINE + "\nNice! I've marked this task as done:\n  " + task + "\n" + LINE;
    }

    /**
     * Displays a message that a task has been marked as undone.
     *
     * @param task the task to be marked as undone
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:\n  " + task.toString());
        System.out.println(LINE);
    }

    public String getTaskUnmarked(Task task) {
        return LINE + "\nOK, I've marked this task as not done yet:\n  " + task + "\n" + LINE;
    }

    /**
     * Displays a message that a new task has been added.
     *
     * @param task the task to be added
     * @param tasks the current list of tasks
     */
    public void showTaskAdded(Task task, List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:" +
                "\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public String getTaskAdded(Task task, List<Task> tasks) {
        return LINE + "\nGot it. I've added this task:\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message that a new task has been deleted.
     *
     * @param task the task to be deleted
     * @param tasks the current list of tasks
     */
    public void showTaskDeleted(Task task, List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:" +
                "\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public String getTaskDeleted(Task task, List<Task> tasks) {
        return LINE + "\nNoted. I've removed this task:\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message showing the error.
     *
     * @param message the error details
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public String getError(String message) {
        return LINE + "\n" + message + "\n" + LINE;
    }

    /** Displays the result of searching the given keyword.
     *
     * @param tasks task list containing the searched keyword
     */
    public void showFoundResults(ArrayList<Task> tasks) {
        System.out.println(getFoundResults(tasks));
    }

    public String getFoundResults(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        if (tasks.isEmpty()) {
            sb.append("No matching tasks found\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        sb.append(LINE);
        return sb.toString();
    }

    /** Displays a task that has been tagged with additional information. */
    public void showTaskTagged(Task task) {
        System.out.println(LINE);
        System.out.println("Tagged task: " + task);
        System.out.println(LINE);
    }

    /**
     * Returns a message showing that a task has been tagged.
     *
     * @param task the task that has been tagged
     * @return message showing tagged task information
     */
    public String taskTaggedMsg(Task task) {
        return LINE + "\nTagged task: " + task + "\n" + LINE;
    }
}
