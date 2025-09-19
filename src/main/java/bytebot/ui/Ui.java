package bytebot.ui;

import java.util.Scanner;

import bytebot.task.Task;
import bytebot.task.TaskList;

/**
 * Handles all user-facing input and output
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
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
    public String showGreeting() {
        String greeting = LINE
                + "Hey there! I'm here to byte down your tasks.\nWhat can I do for you today?\n"
                + LINE;
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Shows the farewell banner.
     */
    public String showFarewell() {
        String farewell = "\t" + LINE
                + "\t" + "Bytebye, Looking forward to helping you again soon.\n"
                + "\t" + LINE;
        System.out.println(farewell);
        return farewell;
    }

    /**
     * Shows an error message.
     *
     * @param message Error to display
     */
    public String showError(String message) {
        String error = "\t" + LINE
                + "\t" + message + "\n"
                + "\t" + LINE;
        System.out.println(error);
        return error;
    }

    /**
     * Displays a numbered list of tasks.
     *
     * @param taskList TaskList to display
     */
    public String showTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the byte tasks what's on your list:");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("\n\t").append(i + 1).append(".")
                    .append(taskList.get(i).toString());
        }
        String result = "\t" + LINE
                + "\t" + output + "\n"
                + "\t" + LINE;
        System.out.println(result);
        return result;
    }

    /**
     * Displays tasks that match a search term.
     *
     * @param taskList Tasks matching the search
     */
    public String showMatchingTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are your byte sized tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("\n\t").append(i + 1).append(".")
                  .append(taskList.get(i).toString());
        }
        String result = "\t" + LINE
                + "\t" + output + "\n"
                + "\t" + LINE;
        System.out.println(result);
        return result;
    }

    /**
     * Shows news after a task is added.
     *
     * @param task The added task
     * @param total Total number of tasks after addition
     */
    public String showAddedTask(Task task, int total) {
        String result = "\t" + LINE
                + "\t" + "Got it, I've added this task:\n\t  " + task
                + "\n\tNow you have " + total + " tasks in the list."
                + "\n" + "\t" + LINE;
        System.out.println(result);
        return result;
    }

    /**
     * Shows a notification after a task is marked as done.
     *
     * @param task The task that was marked done
     */
    public String showTaskMarkedNotification(Task task) {
        String result = "\t" + LINE
                + "\t" + "Nice! I've marked this task as done:\n\t  " + task
                + "\n" + "\t" + LINE;
        System.out.println(result);
        return result;
    }

    /**
     * Shows news after a task is unmarked.
     *
     * @param task The task that was unmarked
     */
    public String showUnmarked(Task task) {
        String result = "\t" + LINE
                + "\t" + "OK, I've marked this task as not done yet:\n\t  " + task
                + "\n" + "\t" + LINE;
        System.out.println(result);
        return result;
    }

    /**
     * Shows news after a task is deleted.
     *
     * @param removed The removed task
     * @param total Total number of tasks after deletion
     */
    public String showDeleted(Task removed, int total) {
        String result = "\t" + LINE
                + "\t" + "I have removed this task:\n\t  " + removed
                + "\n\tNow you have " + total + " tasks in the list."
                + "\n" + "\t" + LINE;
        System.out.println(result);
        return result;
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


