package talkgpt.ui;

import java.util.Scanner;

import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;

/**
 * Handles all user interactions for the TalkGPT application.
 * Provides methods to display messages, read user input, and format responses.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Ui instance for user interaction.
     */
    public Ui() {

    }

    /**
     * Formats a message with the current number of tasks.
     *
     * @param message The message to display.
     * @param size The number of tasks in the list.
     * @return The formatted message string.
     */
    private String formatMessage(String message, int size) {
        String display = String.format("Now you have %d tasks in the list.\n", size);

        return message + display;
    }

    /**
     * Prints the goodbye page of TalkGPT.
     *
     * @return The goodbye message string.
     */
    public String goodbye() {
        String goodbye = """
                Bye. Hope to see you again soon!
                """;
        return goodbye;
    }

    /**
     * Prints the add task message.
     *
     * @param task Task to be added.
     * @param size Size of the task list.
     * @return The add task confirmation message.
     */
    public String addTask(Task task, int size) {
        String header = "Got it. I've added this task:\n";

        return formatMessage(header + " " + task + "\n", size);
    }

    /**
     * Prints the delete task message.
     *
     * @param task Task to be deleted.
     * @param size Size of the task list.
     * @return The delete task confirmation message.
     */
    public String deleteTask(Task task, int size) {
        String msg = "Noted. I've removed this task:\n" + " " + task + "\n";

        return formatMessage(msg, size);
    }

    /**
     * Prints the mark task message.
     *
     * @param task Task to be marked as done.
     * @param size Size of the task list.
     * @return The mark task confirmation message.
     */
    public String markTask(Task task, int size) {
        String msg = "Nice! I've marked this task as done:\n" + " " + task + "\n";

        return formatMessage(msg, size);
    }

    /**
     * Prints the unmark task message.
     *
     * @param task Task to be unmarked.
     * @param size Size of the task list.
     * @return The unmark task confirmation message.
     */
    public String unmarkTask(Task task, int size) {
        String msg = "OK, I've marked this task as not done yet:\n" + " " + task + "\n";

        return formatMessage(msg, size);
    }

    /**
     * Prints the list of tasks.
     *
     * @param list The TaskList containing all tasks.
     * @return The formatted list of tasks.
     */
    public String listView(TaskList list) {
        String msg = "Here are the tasks in your list:\n" + list + "\n";

        return formatMessage(msg, list.size());
    }

    /**
     * Prints the matching tasks found in the list.
     *
     * @param tasks The TaskList containing matching tasks.
     * @return The formatted matching tasks message.
     */
    public String findTask(TaskList tasks) {
        String msg = "Here are the matching tasks in the list:\n" + tasks.toString() + "\n";

        return formatMessage(msg, tasks.size());
    }

    /**
     * Prints the tasks with the specified tag.
     *
     * @param list The TaskList containing all tasks.
     * @param tag  The tag to filter tasks by.
     * @return The formatted tasks with the specified tag message.
     */
    public String tagView(TaskList list, String tag) {
        TaskList taggedTasks = list.getTasksByTag(tag);
        String msg;
        if (taggedTasks.size() == 0) {
            msg = String.format("There are no tasks with the tag: %s\n", tag);
        } else {
            msg = String.format("Here are the tasks with the tag: %s\n%s\n", tag, taggedTasks.toString());
        }

        return formatMessage(msg, taggedTasks.size());
    }
}
