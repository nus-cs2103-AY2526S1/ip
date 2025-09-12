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
        
        // Assert that the output buffer is properly initialized
        assert this.output != null : "Output buffer must be initialized";
        assert this.output.length() == 0 : "Output buffer should start empty";
    }

    /**
     * Gets the captured output as a string.
     *
     * @return The captured output from command execution.
     */
    public String getOutput() {
        // Assert that output buffer is in a valid state
        assert output != null : "Output buffer should never be null";
        
        String result = output.toString().trim();
        
        // Assert postcondition: result should never be null
        assert result != null : "getOutput() should never return null";
        
        return result;
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
        // Assert precondition: error message should not be null
        assert message != null : "Error message should not be null";
        
        appendLine(message);
    }

    @Override
    public void showMessage(String message) {
        // Assert precondition: message should not be null
        assert message != null : "Message should not be null";
        
        appendLine(message);
    }

    @Override
    public void printTaskList(ArrayList<Task> tasks) {
        // Assert precondition: task list should not be null
        assert tasks != null : "Task list should not be null";
        
        if (tasks.isEmpty()) {
            appendLine("Your task list is empty.");
        } else {
            appendLine("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                // Assert loop invariants and boundary conditions
                assert i >= 0 : "Loop index should never be negative";
                assert i < tasks.size() : "Loop index should be within bounds";
                assert tasks.get(i) != null : "Individual tasks should not be null";
                
                // Assert numbering assumption: tasks are numbered starting from 1
                int taskNumber = i + 1;
                assert taskNumber > 0 : "Task numbers should be positive";
                assert taskNumber <= tasks.size() : "Task number should not exceed list size";
                
                appendLine(taskNumber + "." + tasks.get(i).toString());
            }
        }
    }

    @Override
    public void printMatchingTasks(ArrayList<TaskList.IndexedTask> matchingTasks) {
        // Assert precondition: matching tasks list should not be null
        assert matchingTasks != null : "Matching tasks list should not be null";
        
        if (matchingTasks.isEmpty()) {
            appendLine("No matching tasks found.");
        } else {
            appendLine("Here are the matching tasks in your list:");
            for (TaskList.IndexedTask indexedTask : matchingTasks) {
                // Assert that each indexed task and its components are not null
                assert indexedTask != null : "IndexedTask should not be null";
                assert indexedTask.getTask() != null : "Task within IndexedTask should not be null";
                assert indexedTask.getIndex() > 0 : "Task index should be positive";
                
                // Assert that the task's toString() method returns a valid string
                String taskString = indexedTask.getTask().toString();
                assert taskString != null : "Task toString() should not return null";
                assert !taskString.trim().isEmpty() : "Task toString() should not return empty string";
                
                appendLine(indexedTask.getIndex() + "." + taskString);
            }
        }
    }
}
