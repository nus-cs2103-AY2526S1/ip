package cate.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cate.task.Task;
import cate.task.TaskList;

/**
 * Handles all user interface interactions for the Cate application.
 * The {@code Ui} class is responsible for formatting output messages
 * related to tasks and commands. It does not handle actual display;
 * messages are returned as strings to be shown in the GUI or console.
 */
public class Ui {

    /**
     * Formats a list of tasks for display.
     *
     * @param tasks the task list to display
     * @return a formatted string listing all tasks with indices
     */
    public String printList(TaskList tasks) {
        List<Task> list = tasks.getList();
        String tasksStr = IntStream.range(0, list.size())
                .mapToObj(i -> String.format("    %d. %s", i + 1, list.get(i)))
                .collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + tasksStr;
    }

    /**
     * Formats a message indicating a task has been added.
     *
     * @param t the task that was added
     * @return a formatted string confirming the addition
     */
    public String addTask(Task t) {
        return String.format("Got it. I've added this task:\n%s", t);
    }

    /**
     * Formats a message indicating a task has been deleted.
     *
     * @param t    the task that was deleted
     * @param size the current number of tasks remaining
     * @return a formatted string confirming the deletion and showing remaining tasks
     */
    public String deleteTask(Task t, int size) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", t, size);
    }

    /**
     * Formats a message indicating a previously added task has been undone.
     *
     * @param t the task that was removed
     * @return a formatted string confirming the undo of an addition
     */
    public String undoAddTask(Task t) {
        return String.format("OK, I've removed the task you just added:\n  %s", t);
    }

    /**
     * Formats a message indicating a previously deleted task has been restored.
     *
     * @param t the task that was restored
     * @return a formatted string confirming the undo of a deletion
     */
    public String undoDeleteTask(Task t) {
        return String.format("OK, I've restored the task you just deleted:\n  %s", t);
    }

    /**
     * Formats a message indicating a task has been marked as completed.
     *
     * @param t the task that was marked
     * @return a formatted string confirming the task is done
     */
    public String markTask(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s", t);
    }

    /**
     * Formats a message indicating a task has been unmarked as incomplete.
     *
     * @param t the task that was unmarked
     * @return a formatted string confirming the task is not done yet
     */
    public String unmarkTask(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n%s", t);
    }
}
