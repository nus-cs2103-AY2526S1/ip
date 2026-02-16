package jibjab;

import java.util.Scanner;

/**
 * Handles all console input and output interactions for the JibJab application.
 * Provides helper methods to read user commands and common UI messages.
 */
public class Ui {
    private Scanner input;

    /**
     * Creates a Ui instance backed by System.in for reading user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Returns the welcome banner shown when the application starts.
     */
    public String showWelcome() {
        return "Hello from JibJab\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message shown when the application exits.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the message indicating loading tasks from storage failed and a new list will be created.
     */
    public String showLoadingError() {
        return "Failed to load tasks from file...creating new task list";
    }

    /**
     * Reads the next line command from the input stream, or returns an empty string if none is available.
     *
     * @return the next command line entered by the user, or an empty string when input is exhausted
     */
    public String readCommand() {
        if (input.hasNextLine()) {
            return input.nextLine();
        }
        return "";
    }

    /**
     * Returns a horizontal divider line used to separate sections of output.
     */
    public String showLine() {
        return "--------------------------------------";
    }

    // Task operation messages
    public String showTaskAdded(Task task, int count) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + count + " tasks in the list";
    }

    public String showTaskDeleted(Task task, int count) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + count + " tasks in the list";
    }

    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
