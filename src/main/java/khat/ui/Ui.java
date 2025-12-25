package khat.ui;

import java.time.LocalDate;
import java.util.Scanner;

import khat.task.Task;
import khat.task.TaskList;

/**
 * Handles all user interface interactions.
 * It displays messages, read user commands, and shows information about tasks.
 */
public class Ui {

    private static final String DIVIDER = "___________________________________";
    private Scanner scanner = new Scanner(System.in);
    private StringBuilder lastMessages = new StringBuilder();

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        showMessage("Hello! I'm Khat.\nStart keeping track of all your tasks by sending a short command!");
    }

    /**
     * Shows exit message.
     */
    public void showExit() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Reads command from user input.
     *
     * @return Command entered by user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Shows divider line. */
    public void showDivider() {
        System.out.println(DIVIDER);
        lastMessages.append("DIVIDER\n");
    }

    /** Shows an error message when loading previous tasks fails. */
    public void showLoadingError() {
        showMessage("Error loading previous tasks! Creating a new task list.");
    }

    /**
     * Shows a custom message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
        lastMessages.append(message).append("\n");
    }

    /**
     * Shows the list of all tasks.
     *
     * @param tasks The task list containing all tasks.
     */
    public void showAllTasks(TaskList tasks) {
        if (!tasks.isEmpty()) {
            showMessage("List of tasks:");
            showTasks(tasks);
        } else {
            showMessage("There are no tasks yet!");
        }
    }

    /**
     * Shows the deadline tasks occurring on a specific date.
     *
     * @param tasks The task list containing filtered tasks.
     * @param date The date to display tasks for.
     */
    public void showTasksOnDate(TaskList tasks, LocalDate date) {
        if (!tasks.isEmpty()) {
            showMessage("Deadlines on " + date + ":");
            showTasks(tasks);
        } else {
            showMessage("No deadlines on " + date);
        }
    }

    /**
     * Shows the tasks in task list with specified keyword.
     *
     * @param tasks The task list containing filtered tasks.
     * @param keyword Keyword to filter tasks by.
     */
    public void showTasksWithKeyword(TaskList tasks, String keyword) {
        if (!tasks.isEmpty()) {
            showMessage("Here are the matching tasks in your list with keyword " + keyword + ":");
            showTasks(tasks);
        } else {
            showMessage("There are no matching tasks!");
        }
    }

    private void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.getTask(i);
            showMessage(i + 1 + ". " + t.toString());
        }
    }

    /**
     * Retrieves messages for GUI
     *
     * @return Output String
     */
    public String consumeLastMessages() {
        String output = lastMessages.toString();
        lastMessages.setLength(0); // clear after consuming
        return output;
    }

}
