package poopiemeow.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;

/**
 * User interface component for the PoopieMeow application.
 * This class handles all user interaction, including displaying messages,
 * reading user input, and formatting task information for display.
 *
 * <p>
 * The UI provides a consistent visual style with decorative lines and
 * clear formatting for different types of messages. It supports displaying:
 * </p>
 * <ul>
 * <li>Welcome and goodbye messages</li>
 * <li>Task lists with numbered items</li>
 * <li>Success and error messages</li>
 * <li>Task status updates (marked/unmarked, added, deleted)</li>
 * <li>Date-specific task filtering</li>
 * </ul>
 *
 * <p>
 * All output is formatted with consistent spacing and decorative borders
 * to provide a clean, professional appearance.
 * </p>
 *
 * @author tch1001
 * @version 1.0
 */
public class Ui {
    /** Decorative line used to separate different sections of output */
    private static final String LINE = "____________________________________________________________";

    /**
     * Displays the welcome message when the application starts.
     * Shows the application name and prompts the user for input.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays a decorative line separator.
     * Useful for creating visual breaks between different sections of output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command from the user via the provided scanner.
     * This method handles the input reading and returns the user's command.
     *
     * @param sc the Scanner object to read input from
     * @return the user's input command as a string
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Displays an error message to the user.
     * The message is formatted with decorative borders and clearly marked as an
     * error.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(" Oops! " + message);
        System.out.println(LINE);
    }

    /**
     * Displays an error message when tasks cannot be loaded from file.
     * This is a special error case that doesn't use the standard error formatting.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays the goodbye message when the application is exiting.
     * Shows a friendly farewell message to the user.
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays the complete list of tasks to the user.
     * Each task is numbered and displayed with its current status and description.
     *
     * @param tasks the list of tasks to display
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     * Shows the task that was marked and confirms the action.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message when a task is marked as not done.
     * Shows the task that was unmarked and confirms the action.
     *
     * @param task the task that was marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message when a task is deleted.
     * Shows the deleted task and reports the new total count of remaining tasks.
     *
     * @param task           the task that was deleted
     * @param remainingTasks the number of tasks remaining in the list
     */
    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message when a new task is added.
     * Shows the new task and reports the new total count of tasks.
     *
     * @param task       the task that was added
     * @param totalTasks the total number of tasks in the list
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays tasks that occur on a specific date.
     * This method filters tasks by date and shows only those that are relevant
     * to the specified date. For deadlines, it checks if the deadline falls on
     * the specified date. For events, it checks if the event starts or ends on
     * the specified date.
     *
     * @param tasks the complete list of tasks to filter
     * @param date  the date to filter tasks by
     */
    public void showTasksOnDate(ArrayList<Task> tasks, LocalDateTime date) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().toLocalDate().equals(date.toLocalDate()) ||
                        event.getEndTime().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            }
        }
        if (count == 0) {
            System.out.println(" No tasks found on this date.");
        }
        System.out.println(LINE);
    }

    /**
     * Displays the matching tasks found by a search operation.
     * 
     * @param matchingTasks the list of tasks that match the search criteria
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    public String getTaskListString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public String getTaskMarkedString(Task task) {
        return "Nice! I've marked this task as done:\n   " + task;
    }

    public String getTaskUnmarkedString(Task task) {
        return "OK, I've marked this task as not done yet:\n   " + task;
    }

    public String getTaskDeletedString(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n   " + task + "\nNow you have " + remainingTasks
                + " tasks in the list.";
    }

    public String getTaskAddedString(Task task, int totalTasks) {
        return "Got it. I've added this task:\n   " + task + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    public String getTasksOnDateString(ArrayList<Task> tasks, LocalDateTime date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(":\n");
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().equals(date.toLocalDate())) {
                    sb.append(" ").append(++count).append(".").append(task).append("\n");
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().toLocalDate().equals(date.toLocalDate()) ||
                        event.getEndTime().toLocalDate().equals(date.toLocalDate())) {
                    sb.append(" ").append(++count).append(".").append(task).append("\n");
                }
            }
        }
        if (count == 0) {
            sb.append(" No tasks found on this date.");
        }
        return sb.toString().trim();
    }

    public String getMatchingTasksString(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
