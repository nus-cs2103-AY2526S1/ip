package charsiew.ui;

import java.util.Scanner;

import charsiew.task.Task;
import charsiew.task.TaskList;

/**
 * Handles all interactions with the user.
 * Responsible for reading user input and displaying messages.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message and lists available commands.
     */
    public String showHelp() {
        return "Hey there! I'm Char Siew, the chatbot your mom wishes you were.\n"
            + "What can I do for you today?\n\n"
            + "   Command: Effect \n"
            + " - list: show all tasks\n"
            + " - todo task_name: add a new todo task\n"
            + " - deadline task_name /by yyyy-mm-dd: add a new deadline\n"
            + " - event /from yyyy-mm-dd /to yyyy-mm-dd: add a new event\n"
            + " - mark task_index: mark the task done\n"
            + " - unmark task_index: mark the task undone\n"
            + " - delete task_index: delete the task\n"
            + " - undo: undo the last command (if applicable)\n"
            + " - find keyword: find all tasks whose name includes the keyword\n"
            + " - bye: exit\n";
    }

    /** Displays a goodbye message. */
    public String showBye() {
        return " See you soon! May your life be less roasted than me. ";
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param t the task that was added
     * @param count the total number of tasks after addition
     */
    public String showAddedTask(Task t, int count) {
        return " Got it. I've added this task:\n"
            + "   " + t.toString()
            + "\n Now you have " + count + " tasks in the plate.";
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task that was marked
     */
    public String showMark(Task task) {
        return " Nice! Treat yourself with more Char Siew :)\n"
            + "   " + task;
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task the task that was unmarked
     */
    public String showUnmark(Task task) {
        return " Noted! Energise yourself with more Char Siew ;)\n"
            + "   " + task;
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the task that was deleted
     * @param taskLeft the number of remaining tasks
     */
    public String showDelete(Task task, int taskLeft) {
        return " Noted. I've removed this task:\n"
            + "   " + task
            + "\n Now you have " + taskLeft + " tasks in the plate.";
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        return " " + message;
    }

    /**
     * Displays all tasks in the TaskList.
     *
     * @param tasks the TaskList containing the tasks
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }

        String ret = "";
        ret += "Here are the tasks in your plate:\n";
        for (int i = 0; i < tasks.size(); i++) {
            ret += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }

    /** Displays a task undone message. */
    public String showUndo() {
        return "Last task undone!";
    }

    /** Displays a message that the task cannot be undone. */
    public String showNoUndo() {
        return "Oops! The last task cannot be undone. ";
    }

    /** Displays a message that no last command to be undone. */
    public String showNoLastCommand() {
        return "There is no last command to be undone yet!";
    }
}
