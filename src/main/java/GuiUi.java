import java.util.ArrayList;

import application.TaskList;
import application.Ui;
import tasks.Task;

/**
 * A UI class specifically designed for the GUI that captures output as strings
 * instead of printing to console. This allows the GUI to display the output
 * in dialog boxes.
 */
public class GuiUi extends Ui {
    private final StringBuilder output;

    public GuiUi() {
        super();
        this.output = new StringBuilder();
    }

    /**
     * Gets the captured output as a string.
     *
     * @return The captured output from command execution.
     */
    public String getOutput() {
        return output.toString().trim();
    }

    /**
     * Clears the captured output buffer.
     */
    public void clearOutput() {
        output.setLength(0);
    }

    /**
     * Helper method to append a line to output with proper newline handling.
     */
    private void appendLine(String line) {
        if (output.length() > 0) {
            output.append("\n");
        }
        output.append(line);
    }

    @Override
    public void welcome() {
        appendLine("____________________________________________________________");
        appendLine("Hello! I'm Romidas");
        appendLine("What can I do for you?");
        appendLine("____________________________________________________________");
    }

    @Override
    public String readCommand() {
        return ""; // GUI handles input differently
    }

    @Override
    public void showLine() {
        appendLine("____________________________________________________________");
    }

    @Override
    public void showGoodbye() {
        appendLine("Bye. Hope to see you again soon!");
        appendLine("____________________________________________________________");
    }

    @Override
    public void showLoadingError() {
        appendLine("Error loading tasks from file.");
    }

    @Override
    public void showError(String message) {
        appendLine(message);
    }

    @Override
    public void showMessage(String message) {
        appendLine(message);
    }

    @Override
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            appendLine("Your task list is empty.");
        } else {
            appendLine("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                appendLine((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    @Override
    public void printMatchingTasks(ArrayList<TaskList.IndexedTask> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            appendLine("No matching tasks found.");
        } else {
            appendLine("Here are the matching tasks in your list:");
            for (TaskList.IndexedTask indexedTask : matchingTasks) {
                appendLine(indexedTask.getIndex() + "." + indexedTask.getTask().toString());
            }
        }
    }
}
