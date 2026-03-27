package sunday;

import java.util.List;
import java.util.Scanner;

import task.Task;

/**
 * Handles all interactions with the user: input, output, and displaying messages.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the welcome banner.
     */
    public void welcome() {
        System.out.println(DIVIDER + "\n");
        System.out.println("Hi, I am sunday.Sunday. \nYour personal chatbot. :)");
        System.out.println("How can I help you today?. (0.0)\n");
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Prints the bye msg.
     */
    public void bye() {
        System.out.println("\nSee you next time! :)");
    }

    /**
     * Prints a formatted error message.
     *
     * @param message the error text to show
     */
    public void showError(String message) {
        System.out.println(DIVIDER + "\n");
        System.out.println(" " + message);
        System.out.println(DIVIDER + "\n");    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the input string
     */
    public String readInput() {
        return scanner.nextLine();
    }

    public void showDivider() {
        System.out.println(DIVIDER + "\n");    }

    public void showLoadingError(String message) {
        System.out.println("Error during loading. Starting with an empty list." + message);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the task list to show
     */
    public void displayList(TaskList taskList) {
        assert taskList != null : "Task List must not be null";
        if (taskList.isEmpty()) {
            System.out.println("Your list is empty.");
            return;
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + ". " + task.toString());
        }
    }

    /**
     *Shows the result of the search
     * @param result of search
     */
    public void showFindResult(List<Task> result) {
        if(result.isEmpty()) {
            System.out.println("No matches found. (ouO)");
        } else {
            System.out.println("Here are the tasks that we found:\n");
            for(int i = 0; i < result.size(); i++) {
                System.out.println(i + 1 + ". " + result.get(i));
            }
        }
    }
}
