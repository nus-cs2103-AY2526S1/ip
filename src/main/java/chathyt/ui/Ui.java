package chathyt.ui;

import chathyt.task.Deadline;
import chathyt.task.Event;
import chathyt.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interaction for Duke.
 * <p>
 * The {@code Ui} class is responsible for reading user input
 * and generating user-facing messages for the console or GUI.
 * </p>
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a welcome message.
     *
     * @return A welcome string.
     */
    public static String showWelcome() {
        return "Good day! I'm ChatHYT.\n" +
                "How can I help you? \n";
    }

    /**
     * Returns a goodbye message.
     *
     * @return A farewell string.
     */
    public static String sayGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns a formatted string listing all tasks.
     *
     * @param tasks The list of tasks to display.
     * @return A numbered string of tasks or a message if the list is empty.
     */
    public static String showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) return "No tasks yet, find yourself some work";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a formatted string of tasks that match a search query.
     *
     * @param tasks The list of matching tasks.
     * @return A numbered string of matching tasks or a message if none found.
     */
    public static String showListToBeFound(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) return "No matching tasks found. Try other keywords?";
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     * @return A string confirming the task completion.
     */
    public static String showMark(Task task) {
        return "Nice to see that you have completed it:\n  " + task;
    }

    /**
     * Returns a message indicating a task has been unmarked as done.
     *
     * @param task The task that was unmarked.
     * @return A string confirming the task unmarking.
     */
    public static String showUnmark(Task task) {
        return "It's ok, take your time:\n  " + task;
    }

    /**
     * Returns a message confirming a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding.
     * @return A string confirming the addition of the task.
     */
    public static String showAddedTask(Task task, int size) {
        return "Got it. I've added this task:\n  "
                + task + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Returns a message confirming a task has been removed.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after removal.
     * @return A string confirming the removal of the task.
     */
    public static String showRemovedTask(Task task, int size) {
        return "Noted. I've removed this task:\n  "
                + task + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Returns an error message to display to the user.
     *
     * @param message The error message.
     * @return The same error message string.
     */
    public static String showError(String message) {
        return message;
    }

    /**
     * Returns a formatted string of tasks scheduled for a given date.
     * <p>
     * Deadlines occurring on the given date and events overlapping the date
     * will be included. Tasks without dates are always included.
     * </p>
     *
     * @param date  The date to filter tasks by.
     * @param tasks The list of all tasks.
     * @return A numbered string of tasks scheduled for the given date.
     */
    public static String displaySchedule(LocalDate date, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int counter = 1;

        for (Task taskToBeChecked : tasks) {
            if (taskToBeChecked instanceof Deadline) {
                Deadline deadline = (Deadline) taskToBeChecked;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    sb.append(counter++).append(". ").append(taskToBeChecked).append("\n");
                }
            } else if (taskToBeChecked instanceof Event) {
                Event event = (Event) taskToBeChecked;
                if (!date.isBefore(event.getFrom().toLocalDate()) && !date.isAfter(event.getTo().toLocalDate())) {
                    sb.append(counter++).append(". ").append(taskToBeChecked).append("\n");
                }
            } else {
                sb.append(counter++).append(". ").append(taskToBeChecked).append("\n");
            }
        }
        return sb.toString().trim();
    }
}

