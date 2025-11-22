package ui;

import java.util.Scanner;

import task.Task;

/**
*  Handles all user-facing messages and simple console I/O for the application.
 */
public class Ui {


    /** Horizontal rule used to separate UI sections. */
    private static final String LINE = "____________________________________________________________";


    /** Prints a horizontal rule. */
    public void showLine() {
        System.out.println(LINE);
    }


    /** Prints the welcome banner. */
    public String showWelcome() {
        showLine();
        System.out.println(" Hello! I'm GenieWeenie");
        System.out.println(" What can I do for you?");
        showLine();
        return "Hello! I'm GenieWeenie\n What can I do for you?";
    }


    /** Prints the goodbye banner. */
    public String showGoodbye() {
        System.out.println(LINE);
        System.out.println("Goodbye!");
        System.out.println(LINE);
        return "Goodbye";
    }


    /**
     * Prints the message indicating a task has been added.
     *
     * @param task the task that was added
     * @param totalTasks the total number of tasks after addition
     */
    public String showAddTask(Task task, int totalTasks) {
        assert task != null : "Task should not be null when displaying add message";
        assert totalTasks >= 0 : "Total tasks cannot be negative";

        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
        return " Got it. I've added this task:\n " + task + " Now you have \n" + totalTasks + " tasks in the list.";
    }


    /**
     * Prints an error message boxed with a horizontal rule.
     *
     * @param message the error message to print
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";

        System.out.println(LINE);
        System.out.println("Error: " + message);
        System.out.println(LINE);
        return "Error: " + message;
    }

    /**
     * Shows message to user
     */
    public String showMessage(String message) {
        return message;
    }


    /**
     * Reads the next line from standard input.
     *
     * @return the line the user entered
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    /** Prints an error indicating that save data could not be loaded. */
    public String showLoadingError() {
        System.out.println(LINE);
        System.out.println("Error loading tasks! Starting with an empty list.");
        System.out.println(LINE);
        return "Error loading tasks! Starting with an empty list.";
    }
}
