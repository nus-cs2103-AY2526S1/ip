package HawkerUncle.ui;

import HawkerUncle.task.Task;
import HawkerUncle.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static String getWelcome() {
        return "Hello! I'm HawkerUncle. What can I do for you?";
    }
    public static String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine().trim();
    }
    public static String showError(String s) {
        return " OOPS!!! " + s;
    }

    /**
     * Prints a message confirming that a task has been added to the list.
     * @param task the task is to be printed
     * @param taskCount The number of tasks currently in the task list.
     */
    public static String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    public static String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public static String showTaskDeleted(Task task, int taskCount) {
        return "Noted. I've removed this task:\n"
                + " " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    public static String showNoTasksFound() {
        return "No tasks found.";
    }

    public static String showMatchingTasks(TaskList tasks) {
        StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            str.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return str.toString();
    }

    public static String showAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }

        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            str.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return str.toString();
    }

    public static String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n"
                + " " + task;
    }

    public static String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + " " + task;
    }

    public static String showReminders(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No upcoming tasks to remind you about!";
        }
        StringBuilder str = new StringBuilder("Here are your upcoming tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            str.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return str.toString();
    }
}
