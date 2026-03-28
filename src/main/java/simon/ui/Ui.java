package simon.ui;

import java.util.Scanner;

import simon.task.Task;
import simon.task.TaskList;

/**
 * Handles user interface for the Simon chatbot. A <code>Ui</code> object manages input and output to the user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message and logo to the user.
     *
     * @return Welcome message to be displayed.
     */
    public String showWelcome() {
        return " Hello! I'm Simon, your automated task manager from AGT\n"
                + " What can I do for you?\n\n"
                + "Available commands:\n"
                + "list - List all tasks\n"
                + "todo <description> - Add a todo\n"
                + "deadline <description> /by <date> - Add a deadline\n"
                + "event <description> /from <start> /to <end> - Add an event\n"
                + "mark <index> - Mark a task as done\n"
                + "unmark <index> - Unmark a task\n"
                + "delete <index> - Delete a task\n"
                + "find <keyword> - Find tasks\n"
                + "on <date> - List tasks on a date\n"
                + "bye - Exit the app\n"
                + "help - Show this help message";
    }

    /**
     * Displays a divider line.
     *
     * @return Divider line to be displayed.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Reads a command from the user input.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error message.
     * @return Error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a loading error message to the user.
     *
     * @return Loading error message to be displayed.
     */
    public String showLoadingError() {
        return " An error occurred while loading tasks.";
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task Task that was added.
     * @param size New number of tasks in the list.
     * @return Message to be displayed.
     */
    public String showAddTask(Task task, int size) {
        return " Got it. I've added this task:\n"
                + "   " + task
                + "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task Task that was deleted.
     * @param size New number of tasks in the list.
     * @return Message to be displayed.
     */
    public String showDeleteTask(Task task, int size) {
        return " Noted. I've removed this task:\n"
                + "   " + task
                + "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays the list of all the tasks to the user.
     *
     * @param tasks List of tasks to be displayed.
     * @return Message listing out all tasks.
     */
    public String showTaskList(TaskList tasks) {
        return " Here are the tasks in your list:\n"
                + tasks.getAll();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task Task that was marked as done.
     * @return Marked task message to be displayed.
     */
    public String showMarkTask(Task task) {
        return " Nice! I've marked this task as done:\n"
                + "   " + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task Task that was marked as not done.
     * @return Unmarked task message to be displayed.
     */
    public String showUnmarkTask(Task task) {
        return " OK, I've marked this task as not done yet:\n"
                + "   " + task;
    }

    /**
     * Displays the list of matching tasks to the user.
     *
     * @param tasks List of tasks to be displayed.
     * @return Message listing out matching tasks.
     */
    public String showFindTasks(TaskList tasks) {
        return " Here are the matching tasks in your list:\n"
                + tasks.getAll();
    }

    /**
     * Displays the help message to the user.
     * 
     * @return Help message to be displayed.
     */
    public String showHelp() {
        return "Available commands:\n"
            + "list - List all tasks\n"
            + "todo <description> - Add a todo\n"
            + "deadline <description> /by <date> - Add a deadline\n"
            + "event <description> /from <start> /to <end> - Add an event\n"
            + "mark <index> - Mark a task as done\n"
            + "unmark <index> - Unmark a task\n"
            + "delete <index> - Delete a task\n"
            + "find <keyword> - Find tasks\n"
            + "on <date> - List tasks on a date\n"
            + "bye - Exit the app\n"
            + "help - Show this help message";
    }

    /**
     * Displays the goodbye message to the user.
     * 
     * @return Goodbye message to be displayed.
     */
    public String showGoodbye() {
        return " Bye. Hope to see you again soon!";
    }
}