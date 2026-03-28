package karl.ui;

import karl.task.Task;
import karl.task.TaskList;
import java.util.Scanner;

/**
 * Handles all user interactions for Karl.
 * Collects all messages in a buffer for GUI retrieval.
 */
public class Ui {
    private StringBuilder responseBuffer = new StringBuilder();

    //  Methods for GUI support

    /** Clears the buffer before each response. */
    private void clearBuffer() {
        responseBuffer.setLength(0);
    }

    /** Returns the latest response as a string, then clears buffer. */
    public String getLastMessage() {
        String res = responseBuffer.toString();
        clearBuffer();
        return res;
    }

    /**
     * Returns the welcome message string shown to the user at the start of the program.
     *
     * @return a greeting message introducing the program and prompting for input
     */
    public String showWelcome() {
        return "Hello! I'm Karl 🤖\nWhat can I do for you?\n";
    }

    /**
     * Returns the farewell message displayed when the program exits.
     *
     * @return a farewell message bidding goodbye to the user
     */
    public void showGoodbye() {
        clearBuffer();
        responseBuffer.append("Bye. Karl hopes to see you again!\n");
    }

    /**
     * Returns the error message string based on user exception messages.
     *
     * @param message the error message to display
     * @return formatted string representing the error message
     */
    public void showError(String message) {
        clearBuffer();
        responseBuffer.append("Oops! ").append(message).append("\n");
    }

    public void showLoadingError() {
        clearBuffer();
        responseBuffer.append("Error loading tasks. Starting with an empty list.\n");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    /**
     * Returns the message confirming that a task was added, including task details and current total of tasks.
     *
     * @param task the task that was added
     * @param taskCount current total number of tasks after addition
     * @return confirmation message including task description and total count
     */
    public void showAddedTask(Task task, int taskCount) {
        clearBuffer();
        responseBuffer.append("Got it. I've added this task:\n  ").append(task).append('\n');
        responseBuffer.append("Now you have ").append(taskCount)
                .append(" ").append((taskCount == 1) ? "task" : "tasks").append(" in the list.\n");
    }

    /**
     * Returns the message confirming that a task was removed, including task details and current total of tasks.
     *
     * @param task the task that was removed
     * @param taskCount current total number of tasks after removal
     * @return confirmation message including task description and total count
     */
    public void showRemovedTask(Task task, int taskCount) {
        clearBuffer();
        responseBuffer.append("Noted. I've removed this task:\n  ").append(task).append('\n');
        responseBuffer.append("Now you have ").append(taskCount)
                .append(" ").append((taskCount == 1) ? "task" : "tasks").append(" in the list.\n");
    }

    /**
     * Returns the message confirming that a task was marked as done.
     *
     * @param task the task that was marked as done
     * @return confirmation message indicating the task was marked done
     */
    public void showMarkedTask(Task task) {
        clearBuffer();
        responseBuffer.append("Nice! I've marked this task as done:\n  ").append(task).append('\n');
    }

    /**
     * Returns the message confirming that a task was marked as not done (unmarked).
     *
     * @param task the task that was unmarked
     * @return confirmation message indicating the task is now not done
     */
    public void showUnmarkedTask(Task task) {
        clearBuffer();
        responseBuffer.append("OK, I've marked this task as not done yet:\n  ").append(task).append('\n');
    }

    /**
     * Returns a custom arbitrary message.
     *
     * @param message message string to display
     * @return the input message string
     */
    public void showMessage(String message) {
        clearBuffer();
        responseBuffer.append(message).append("\n");
    }

    /**
     * Returns the message displaying the entire list of tasks.
     *
     * @param tasks the list of tasks to display
     * @return a string listing all tasks, each on its own line numbered accordingly
     */
    public void showTaskList(TaskList tasks) {
        clearBuffer();
        if (tasks.size() == 0) {
            responseBuffer.append("(No tasks yet!)\n");
        } else {
            responseBuffer.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                responseBuffer.append((i + 1)).append(". ").append(tasks.get(i)).append('\n');
            }
        }
    }
}
