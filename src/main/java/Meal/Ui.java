package Meal;

import java.util.Scanner;

public class Ui {
    private static final String SEP = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    /**
     * Trims whitespace from the users input
     * @return trimmed user input
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays a separator line
     */
    public void showLine() {
        System.out.println(SEP);
    }

    /**
     * Displays an error message with separator lines
     * @param msg error message to be displayed
     */
    public void showError(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

    /**
     * Displays a loading error message
     */
    public void showLoadingError() {
        showError("Load failed.");
    }

    /**
     * Displays a welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Meal.Meal\nWhat can I do for you?\n");
    }

    /**
     * Displays a goodbye message
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays the list of tasks with numbering
     * @param tasks list of tasks to be displayed
     */
    public void showList(TaskList tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays confirmation message after task is added
     * @param t task that was added
     * @param size total number of tasks after task was added
     */
    public void showAdd(Task t, int size) {
        showLine();
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays confirmation message after task is deleted
     * @param removed task that was deleted
     * @param size total number of tasks after task was deleted
     */
    public void showDelete(Task removed, int size) {
        showLine();
        System.out.println("Got it. I've removed this task:\n" + removed);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a block of text with separators
     * @param lines text to be displayed
     */
    public void showTextBlock(String... lines) {
        showLine();
        for (String line : lines) {
            System.out.println(line);
        }
        showLine();
    }

    /**
     * Displays results of a search operation with all matching tasks
     * @param matches list of matching tasks
     */
    public void showFindResults(java.util.List<Task> matches) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + "." + matches.get(i));
        }
        showLine();
    }

    /**
     * Closes the input scanner
     */
    public void close() {
        sc.close();
    }
}
