package minhgpt.ui;

import java.util.ArrayList;

import minhgpt.task.Task;
import minhgpt.task.TaskList;

/**
 * Responsible for all UI printing.
 */
public class Ui {
    // NOTE: PRIVATE

    /**
     * Return String of 'size' number of empty lines.
     */
    private static String padding(int size) {
        String pad = "";
        for (int i = 0; i < size; i++) {
            pad += "\n";
        }
        return pad;
    }

    // NOTE: PUBLIC

    /**
     * Return initial greeting message when user enter the program.
     */
    public static String welcomeMessage() {
        return "Hello! I'm MinhGPT.\nWhat can I do for you?";
    }

    /**
     * Return the error response when the task with that index does not exist for
     * mark + unmark + delete operation.
     */
    public String indexErrorResponse() {
        return "<( ⸝⸝•̀ - •́⸝⸝)> There is no tasks with that index."
                + " You could have caused an IndexOutOfBoundsException.";
    }

    /**
     * Return the response when user add a task.
     *
     * @param task Task that was just added by user.
     */
    public String addResponse(Task task) {
        return String.format("(˶ᵔ ᵕ ᵔ˶) Added: %s", task);
    }

    /**
     * Return the response when user mark a task.
     *
     * @param task Task that was just marked by user.
     */
    public String markResponse(Task task) {
        return String.format("(˵˃ ᗜ ˂˵) Congrats on finishing the task.\n%s", task);
    }

    /**
     * Return the response when user unmark a task.
     *
     * @param task Task that was just unmarked by user.
     */
    public String unmarkResponse(Task task) {
        return String.format("(¬`‸´¬) Huh? Why did you lie?\n%s", task);
    }

    /**
     * Return the response when user delete a task.
     *
     * @param task Task that was just deleted by user.
     */
    public String deleteResponse(Task task) {
        return String.format("(˶ᵔ ᵕ ᵔ˶) Removed: %s", task);
    }

    /**
     * Return the response when user list all tasks.
     */
    public String listResponse(TaskList taskList) {
        StringBuilder builder = new StringBuilder(String.format(
                "(˶˃ ᵕ ˂˶) Here are the list of tasks. You have %d in total.", taskList.size()));
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format("\n%d.%s", i + 1, taskList.get(i)));
        }
        return builder.toString();
    }

    /**
     * Return the response when user wanted to find tasks.
     *
     * @param tasks Tasks matching the query that user inputted.
     */
    public String findResponse(ArrayList<Task> tasks) {
        StringBuilder builder = new StringBuilder(
                String.format("(˶˃ ᵕ ˂˶) Here are the matching tasks in your list.", tasks.size()));
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("\n%d.%s", i + 1, tasks.get(i)));
        }
        return builder.toString();
    }

    /**
     * Return the message when user exit the program.
     */
    public String exitMessage() {
        return "(╥﹏╥) Bye. Hope to see you again soon!";
    }
}
