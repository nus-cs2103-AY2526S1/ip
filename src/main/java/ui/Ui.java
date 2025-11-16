package ui;

import task.Task;

/**
 * Ui class handles the user interface interactions.
 */
public class Ui {
    /**
     * Displays the introduction message to the user.
     */
    public static String showIntro() {
        String intro = """
                 Hello! I'm Frenny ;)
                 What can I do for you?
                """;
        System.out.println(intro);
        return intro;
    }

    /**
     * Displays the outro message to the user.
     */
    public static String showOutro() {
        String outro = "Bye. Hope to see you again soon! :D";
        System.out.println(outro);
        return outro;
    }

    /**
     * Displays a line separator for better readability.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the message indicating the start of the task list.
     */
    public static String showListMessage(int listSize) {
        String message;
        if (listSize == 0) {
            message = "No task for now :P";
        } else {
            message = "Here are the current tasks in your list:";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating tasks have been deleted.
     *
     * @param tasks The tasks that have been deleted.
     */
    public static String showDeleteMessage(Task... tasks) {
        StringBuilder message = new StringBuilder("Noted. I've removed these tasks:\n");
        for (Task task : tasks) {
            assert task != null : "Task cannot be null";
            message.append(task).append("\n");
        }
        System.out.println(message);
        return message.toString();
    }

    /**
     * Displays the message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public static String showAddMessage(Task task) {
        assert task != null : "Task cannot be null";
        String message = "Got it. I've added this task:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the current size of the task list.
     *
     * @param listSize The current number of tasks in the list.
     */
    public static String showListSize(int listSize) {
        String message;
        if (listSize == 1) {
            message = "Now you have " + listSize + " task in the list.";
        } else {
            message = "Now you have " + listSize + " tasks in the list.";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Displays the message indicating tasks have been marked as done.
     *
     * @param tasks The tasks that has been marked as done.
     */
    public static String showMarkMessage(Task... tasks) {
        StringBuilder message = new StringBuilder("Nice! I've marked these tasks as done:\n");
        for (Task task : tasks) {
            assert task != null : "Task cannot be null";
            message.append(task).append("\n");
        }
        System.out.println(message);
        return message.toString();
    }

    /**
     * Displays the message indicating a task has been unmarked as not done.
     *
     * @param tasks The task that has been unmarked as not done.
     */
    public static String showUnmarkMessage(Task... tasks) {
        StringBuilder message = new StringBuilder("OK, I've marked these tasks as not done yet:\n");
        for (Task task : tasks) {
            assert task != null : "Task cannot be null";
            message.append(task).append("\n");
        }
        System.out.println(message);
        return message.toString();
    }

    /**
     * Displays the message indicating the start of the search results.
     */
    public static String showFindMessage() {
        String message = "Here are the matching tasks in your list:";
        System.out.println(message);
        return message;
    }
}
