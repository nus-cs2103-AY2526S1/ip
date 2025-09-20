package lyra;

import lyra.task.Task;
import lyra.task.TaskList;
import lyra.util.Ui;

import java.util.ArrayList;

/**
 * GUI-compatible UI class that captures output as strings instead of printing to console.
 * This allows the GUI to display messages without using System.out.println.
 */
public class GuiUi extends Ui {
    private StringBuilder outputBuffer;

    /**
     * Constructs a new GuiUi instance.
     */
    public GuiUi() {
        this.outputBuffer = new StringBuilder();
        assert this.outputBuffer != null : "output buffer must be initialized";
    }

    /**
     * Gets the current output buffer content.
     */
    public String getOutput() {
        return outputBuffer.toString();
    }

    /**
     * Clears the output buffer.
     */
    public void clearOutput() {
        outputBuffer.setLength(0);
    }

    // Override methods from Ui to capture output instead of printing
    @Override
    public void showWelcome() {
        String welcome = "Hello! I'm Lyra\nWhat can I do for you?";
        outputBuffer.append(welcome);
    }

    @Override
    public void showGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        outputBuffer.append(goodbye);
    }

    @Override
    public void showLine() {
        String line = "____________________________________________________________";
        outputBuffer.append(line);
    }

    @Override
    public void showEmptyCommandMessage() {
        String message = "Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.";
        outputBuffer.append(message);
    }

    @Override
    public void showUnknownCommandMessage() {
        String message = "Sorry, I couldn't recognize that command. Try one of: list, todo, deadline, event, mark, unmark, delete, bye.";
        outputBuffer.append(message);
    }

    @Override
    public void showTaskList(ArrayList<Task> tasks) {
        assert tasks != null : "tasks must not be null";
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("There are no tasks in your list.");
        } else {
            message.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        String result = message.toString().trim();
        outputBuffer.append(result);
    }

    @Override
    public void showMatchingTasks(ArrayList<Task> tasks) {
        assert tasks != null : "tasks must not be null";
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("No matching tasks found.");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        String result = message.toString().trim();
        outputBuffer.append(result);
    }

    @Override
    public void showTaskAdded(Task task, int totalTasks) {
        assert task != null : "task must not be null";
        assert totalTasks >= 0 : "totalTasks must be non-negative";
        String message = "Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + totalTasks + " tasks in the list.";
        outputBuffer.append(message);
    }

    @Override
    public void showTaskMarked(Task task, boolean isDone) {
        assert task != null : "task must not be null";
        String message;
        if (isDone) {
            message = "Nice! I've marked this task as done:\n  " + task.toString();
        } else {
            message = "OK, I've marked this task as not done yet:\n  " + task.toString();
        }
        outputBuffer.append(message);
    }

    @Override
    public void showTaskDeleted(Task task, int remainingTasks) {
        assert task != null : "task must not be null";
        assert remainingTasks >= 0 : "remainingTasks must be non-negative";
        String message = "Noted. I've removed this task:\n  " + task.toString() + "\nNow you have " + remainingTasks + " tasks in the list.";
        outputBuffer.append(message);
    }

    @Override
    public void showInvalidTaskNumber() {
        String message = "Invalid task number.";
        outputBuffer.append(message);
    }

    @Override
    public void showTodoErrorMessage() {
        String message = "Sorry, a todo needs a description. Try: todo <description>";
        outputBuffer.append(message);
    }

    @Override
    public void showDeadlineErrorMessage() {
        String message = "Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>";
        outputBuffer.append(message);
    }

    @Override
    public void showEventErrorMessage() {
        String message = "Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>";
        outputBuffer.append(message);
    }

    @Override
    public void showDeleteErrorMessage() {
        String message = "Please specify a task number to delete. Try: delete <task_number>";
        outputBuffer.append(message);
    }

    @Override
    public void showLoadingError() {
        String message = "Error loading tasks from file. Starting with empty task list.";
        outputBuffer.append(message);
    }

    @Override
    public void showError(String message) {
        assert message != null : "error message must not be null";
        outputBuffer.append(message);
    }

    @Override
    public String showMessage(String message) {
        outputBuffer.append(message);
        return message;
    }

    @Override
    public void close() {
        // No need to close scanner in GUI
    }
}
