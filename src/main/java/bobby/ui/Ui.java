package bobby.ui;

import bobby.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interaction by displaying messages and reading input from the console.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    public static final String WELCOME_MESSAGE = "hi im Bobby\nWhat can I do for you today?";

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error message to display.
     */
    public void showError(String msg) {
        assert msg != null : "Error message should not be null";
        System.out.println("Error: " + msg);
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedDone(Task task) {
        assert task != null : "Task should not be null";
        System.out.println("Task marked as done:");
        System.out.println("  " + task);
    }

    public void showTaskMarkedNotDone(Task task) {
        assert task != null : "Task should not be null";
        System.out.println("Task marked as not done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        assert task != null : "Task should not be null";
        System.out.println("Task added:");
        System.out.println("  " + task);
        System.out.println("You now have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public void showTaskDeleted(Task task, int size) {
        assert task != null : "Task should not be null";
        System.out.println("Task removed:");
        System.out.println("  " + task);
        System.out.println("You now have " + size + " tasks in the list.");
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task in list should not be null";
            System.out.println((i + 1) + ". " + task);
        }
    }

    /**
     * Displays the list of found tasks matching a search keyword.
     *
     * @param tasks The list of matching tasks to display.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task in found list should not be null";
            System.out.println((i + 1) + ". " + task);
        }
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("____________________________");
    }

    /**
     * Reads the next command from the user input.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param msg The error message to display.
     */
    public void showLoadingError(String msg) {
        System.out.println("Error loading tasks: " + msg);
    }

    /**
     * Displays a generic message to the user.
     *
     * @param msg The message to display.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays a message indicating a task has been updated.
     *
     * @param task The task that was updated.
     * @return The update confirmation message.
     */
    public String showUpdate(Task task) {
        String msg = "Task updated:\n  " + task;
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the schedule for a specific date.
     *
     * @param date The date for which to show the schedule.
     * @param tasks The list of tasks scheduled for that date.
     * @return The schedule as a string (for GUI or CLI).
     */
    public String showScheduleForDate(LocalDate date, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule for ").append(date).append(":\n");
        if (tasks.isEmpty()) {
            sb.append("No tasks scheduled for this date.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        String result = sb.toString();
        System.out.print(result);
        return result;
    }
}
