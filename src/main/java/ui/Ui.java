package ui;

import java.util.List;

import models.Task;
import models.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Constructs a new Ui instance with a scanner for user input
     */
    public Ui() {
    }

    /**
     * Displays the greeting message when the chatbot starts
     */
    public String getGreetResponse() {
        return "Hello! I'm Yapper. \nWhat can I do for you?";
    }

    /**
     * Displays the exit message when the chatbot terminates
     */
    public String getExitResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays all tasks in the task list
     *
     * @param tasks the TaskList containing tasks to display
     */
    public String getListResponse(TaskList tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }

    /**
     * Displays the result of marking or unmarking a task
     *
     * @param task   the task that was marked/unmarked
     * @param isDone true if the task was marked as done, false if unmarked
     */
    public String getMarkTaskResponse(Task task, boolean isDone) {
        StringBuilder response = new StringBuilder();
        if (isDone) {
            response.append("Nice! I have marked this task as done:\n");
        } else {
            response.append("Ok, I have marked this task as not done yet:\n");
        }
        response.append(task + "\n");
        return response.toString();
    }

    /**
     * Displays the result of deleting a task
     *
     * @param task      the task that was deleted
     * @param taskCount the new total number of tasks after deletion
     */
    public String getDeleteTaskResponse(Task task, int taskCount) {
        StringBuilder response = new StringBuilder();
        response.append("Noted. I have removed this task:\n");
        response.append(task + "\n");
        response.append(String.format("Now you have %d tasks in the list.\n", taskCount));
        return response.toString();
    }

    /**
     * Displays the result of adding a new task
     *
     * @param task      the task that was added
     * @param taskCount the new total number of tasks after addition
     */
    public String getAddTaskResponse(Task task, int taskCount) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append(task + "\n");
        response.append(String.format("Now you have %d tasks in the list.\n", taskCount));
        return response.toString();
    }

    /**
     * Displays the result of adding a new task
     *
     * @param filteredTasks the task that was added
     */
    public String getFindResponse(List<Task> filteredTasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, filteredTasks.get(i)));
        }
        return response.toString();
    }

    /**
     * Displays an error message to the user
     *
     * @param errorMessage the error message to display
     */
    public String getErrorResponse(String errorMessage) {
        return errorMessage;
    }
}
