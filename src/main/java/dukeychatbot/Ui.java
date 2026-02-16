package dukeychatbot;

import java.util.ArrayList;

import dukeychatbot.tasktypes.Task;

/**
 * Constructs the UI class which deals with interactions with the user.
 *
 * @author dongjun
 */
public class Ui {

    private final String horizontalLine = "______________________________________\n";

    public Ui() {}

    /**
     * Displays hello text to signal start of the chatbot.
     */
    public String hello(ArrayList<Task> tasks) {
        return horizontalLine
                + "Pika Pika! Hello! I'm Dukey!! ⚡\n"
                + "You have " + tasks.size() + " tasks in your list.\n"
                + "What can I do for you, pika?\n"
                + horizontalLine;
    }

    /**
     * Displays bye text to signal the termination of the chatbot.
     */
    public String bye() {
        return horizontalLine
                + "Pika… bye-bye! 🐾\n"
                + "Hope to see you again soon, pika!\n"
                + "This chat will close in 5 seconds, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays list of tasks.
     *
     * @param tasks ArrayList of type Task.
     */
    public String printList(String header, ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(horizontalLine).append("Here's your list, pika pika ⚡:\n");
        for (int count = 1; count <= tasks.size(); count++) {
            Task currentTask = tasks.get(count - 1);
            taskList.append(count).append(". ").append(currentTask.toString()).append("\n");
        }
        taskList.append(horizontalLine);

        return taskList.toString();
    }

    /**
     * Displays error message for when task index is invalid.
     */
    public String invalidTaskIndex() {
        return horizontalLine
                + "Pika pika! That task number is too big, pika!\n"
                + "Try again with a smaller number, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays error message for when number format was inputted wrongly.
     */
    public String numberFormatError() {
        return horizontalLine
                + "Oops, pika! That doesn't look like a number.\n"
                + "Make sure you type an integer, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays message when no matching tasks are found.
     */
    public String noMatchingTasks() {
        return horizontalLine
                + "Pika… I couldn't find any tasks matching that keyword.\n"
                + "Try another keyword, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays text to inform user that chatbox has been terminated.
     */
    public String chatboxClosedResponse() {
        return horizontalLine
                + "Pika! The chatbox is closed now. 💤\n"
                + "You can reopen it anytime to continue our chat, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays text for task removal from task list.
     */
    public String removeTaskResponse(String taskDescription, int taskNo) {
        return horizontalLine
                + "Pika! I removed this task, pika:\n"
                + taskDescription
                + "\nNow you have " + taskNo + " tasks left, pika!\n"
                + horizontalLine;
    }

    /**
     * Displays text to acknowledge marking of a task as done.
     */
    public String markDoneResponse(String taskDescription) {
        return horizontalLine
                + "Pika Pika! Task completed, pika! ✅\n"
                + taskDescription
                + "\n"
                + horizontalLine;
    }

    /**
     * Displays text to acknowledge unmarking of a task as done.
     */
    public String unmarkDoneResponse(String taskDescription) {
        return horizontalLine
                + "Pika… I’ve unmarked this task, pika:\n"
                + taskDescription
                + "\n"
                + horizontalLine;
    }

    /**
     * Displays text for successful task adding to the task list.
     */
    public String addTaskResponse(String taskDescription, int taskNo) {
        return horizontalLine
                + "Pika Pika! Added a new task, pika:\n"
                + taskDescription
                + "\nYou now have " + taskNo + " tasks in total, pika! ⚡\n"
                + horizontalLine;
    }

    /**
     * Displays formatted error response.
     */
    public String formattedErrorResponse(String errorMessage) {
        return horizontalLine + "Pika… " + errorMessage + "\n" + horizontalLine;
    }
}
