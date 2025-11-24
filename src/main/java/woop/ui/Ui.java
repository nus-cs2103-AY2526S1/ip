package woop.ui;

import woop.logic.TaskList;

/**
 * Class to output text by Woop.
 */
public class Ui {
    public static final String INDENT = "  ";

    public static String showIntro() {
        return "Upah! I'm Woop\nWhat can I do for you?";
    }

    public static String showGoodbye() {
        return "Upah! Hope to see you again soon!";
    }

    public static String showTaskList(TaskList list) {
        if (list.isEmpty()) {
            return "Upah! Feed me some tasks to track!";
        } else {
            return "Upah! I have detected several tasks\n" + list.toString();
        }

    }

    public static String showFindKeyword(TaskList list) {
        if (list.isEmpty()) {
            return "Upah! There are no matching tasks in your list";
        } else {
            return "Upah! Here are the matching tasks in your list\n" + list.toString();
        }
    }

    public static String showMarked(String task) {
        return "Upah! This task has been completed!\n" + INDENT + task;
    }

    public static String showUnmarked(String task) {
        return "Upah? The task has was not completed after all :(\n" + INDENT + task;
    }

    public static String showDeleted(String task) {
        return "Upah! I have deleted the task\n" + INDENT + task;
    }

    public static String showAddTask(String task, int size) {
        return "Upah! You're busy! I added this task:\n"
                + INDENT + task + "\nYou have " + size + " task(s) now";
    }

    public static String showCommandError(String command) {
        return "Upah! What is " + command + "?";
    }

    public static String showDescriptorError(String command) {
        return "Upah! The command " + command + " needs a valid description!";
    }

    public static String showError(Exception e) {
        return e.getMessage();
    }

    public static String showIoError() {
        return "Upah! I cannot access your saved tasks";
    }

    public static String showTagTask(String task) {
        return "Upah! I have tagged this task:\n" + INDENT + task;
    }
}
