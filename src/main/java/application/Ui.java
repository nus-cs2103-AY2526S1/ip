package application;

import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;

/**
 * Handles user interface operations including input/output and display formatting.
 * Manages user interaction through console input and formatted output display.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     * Shows the application name and initial greeting to the user.
     */
    public void welcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Romidas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line for formatting output.
     * Used to visually separate different sections of the interface.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the application exits.
     * Shows farewell message and separator line.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message when task loading fails.
     * Informs the user that tasks could not be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays the current list of tasks in a formatted manner.
     * Shows each task with its index number and full string representation.
     *
     * @param tasks The list of tasks to display.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
    
    /**
     * Displays the matching tasks from a search operation.
     * Shows each matching task with its original index number from the full task list.
     *
     * @param matchingTasks The list of indexed tasks that matched the search criteria.
     */
    public void printMatchingTasks(ArrayList<TaskList.IndexedTask> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (TaskList.IndexedTask indexedTask : matchingTasks) {
                System.out.println(indexedTask.getIndex() + "." + indexedTask.getTask().toString());
            }
        }
    }
}

