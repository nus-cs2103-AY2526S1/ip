package cheryl.util;

import cheryl.task.Task;

import java.util.Scanner;

/**
 * Handles user interaction.
 * Responsible for showing messages to the user.
 */
public class Ui {
    private Scanner sc;
    private StringBuilder outputBuffer = new StringBuilder();
    private boolean isCliMode = true;

    /**
     * Creates a new Ui object and initializes the input scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void setCliMode(boolean isCli) {
        this.isCliMode = isCli;
    }

    /**
     * Prints a greeting message when the app starts.
     */
    public void showWelcome() {
        String welcome = "Hello! I'm Cheryl\nWhat can I do for you?";
        System.out.println(welcome);
        appendToBuffer(welcome);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The trimmed user input string
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints an error message to the user.
     *
     * @param error The error message to display
     */
    public void showError(String error) {
        if (isCliMode) {
            System.out.println(error);
        }
        appendToBuffer(error);
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to display
     */
    public void showMessage(String message) {
        if (isCliMode) {
            System.out.println(message);
        }
        appendToBuffer(message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    private void appendToBuffer(String s) {
        if (s == null) return;
        if (outputBuffer.length() > 0) {
            outputBuffer.append("\n");
        }
        outputBuffer.append(s);
    }
    /**
     * Returns the last output
     * @return the variable lastOutput
     */
    public String getLastOutput() {
        return outputBuffer.toString();
    }

    /**
     * Set last output to an empty string
     */
    public void clearLastOutput() {
        outputBuffer.setLength(0);
    }

    public void showTaskStatusChanged(Task task, boolean isMarked) {
        if (isMarked) {
            showMessage("Nice! I've marked this task as done:");
        } else {
            showMessage("OK, I've marked this task as not done yet:");
        }
        showMessage(task.toString());
    }

}