package lyra.util;

import lyra.task.Task;
import lyra.task.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles user interface operations in the Lyra application.
 * Provides methods for displaying messages, reading user input, and formatting output.
 * Manages the Scanner for reading user commands and displays formatted messages with visual separators.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" 🌟 Hello! I'm Lyra, your personal task manager");
        System.out.println(" 💡 Type 'help' to see all available commands");
        System.out.println(" 🚀 What can I do for you today?");
        showLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a visual separator line for formatting output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command from the user via standard input.
     * Returns "bye" if no more input is available to ensure graceful exit.
     *
     * @return the user's command string
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "bye"; // Exit gracefully if no more input
        }
    }

    /**
     * Displays a message prompting the user to enter a command.
     */
    public void showEmptyCommandMessage() {
        showLine();
        System.out.println(" Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.");
        showLine();
    }

    /**
     * Displays a message when an unknown command is entered.
     */
    public void showUnknownCommandMessage() {
        showLine();
        System.out.println(" Sorry, I couldn't recognize that command. Try one of: list, todo, deadline, event, mark, unmark, delete, bye.");
        showLine();
    }

    /**
     * Displays the complete list of tasks with numbered formatting.
     *
     * @param tasks the list of tasks to display
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        showLine();
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param tasks the list of matching tasks to display
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        showLine();
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     *
     * @param task the task that was added
     * @param totalTasks the total number of tasks after addition
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message when a task's status is changed.
     *
     * @param task the task whose status was changed
     * @param isDone true if marked as done, false if marked as not done
     */
    public void showTaskMarked(Task task, boolean isDone) {
        showLine();
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task.toString());
        showLine();
    }

    /**
     * Displays a confirmation message when a task is successfully deleted.
     *
     * @param task the task that was deleted
     * @param remainingTasks the number of tasks remaining after deletion
     */
    public void showTaskDeleted(Task task, int remainingTasks) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays an error message for invalid task numbers.
     */
    public void showInvalidTaskNumber() {
        showLine();
        System.out.println(" Invalid task number.");
        showLine();
    }

    /**
     * Displays an error message for invalid todo command usage.
     */
    public void showTodoErrorMessage() {
        showLine();
        System.out.println(" Sorry, a todo needs a description. Try: todo <description>");
        showLine();
    }

    /**
     * Displays an error message for invalid deadline command usage.
     */
    public void showDeadlineErrorMessage() {
        showLine();
        System.out.println(" Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        showLine();
    }

    /**
     * Displays an error message for invalid event command usage.
     */
    public void showEventErrorMessage() {
        showLine();
        System.out.println(" Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        showLine();
    }

    /**
     * Displays an error message for invalid delete command usage.
     */
    public void showDeleteErrorMessage() {
        showLine();
        System.out.println(" Please specify a task number to delete. Try: delete <task_number>");
        showLine();
    }

    /**
     * Displays an error message when loading tasks from file fails.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(" Error loading tasks from file. Starting with empty task list.");
        showLine();
    }

    /**
     * Displays a generic error message with the specified content.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    public String showMessage(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
        return message;
    }

    /**
     * Closes the Scanner to free system resources.
     */
    public void close() {
        scanner.close();
    }
}
