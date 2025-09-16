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
    // Constants for better readability and maintainability
    private static final String SEPARATOR_LINE = "____________________________________________________________";
    private static final String WELCOME_NAME = "Hello! I'm Romidas";
    private static final String WELCOME_PROMPT = "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LOADING_ERROR_MESSAGE = "Error loading tasks from file.";
    private static final String EMPTY_TASK_LIST_MESSAGE = "Your task list is empty.";
    private static final String TASK_LIST_HEADER = "Here are the tasks in your list:";
    private static final String MATCHING_TASKS_HEADER = "Here are the matching tasks in your list:";
    private static final String NO_MATCHES_MESSAGE = "No matching tasks found.";
    private static final String NEWLINE = "\n";
    private static final String TASK_NUMBER_SEPARATOR = ".";
    private static final String EMPTY_COMMAND = ""; // GUI handles input differently
    
    private final StringBuilder output;

    /**
     * Constructs a new GuiUi instance.
     * Initializes the output buffer for capturing GUI display content.
     */
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
     * Resets the buffer to empty state for new content capture.
     */
    public void clearOutput() {
        output.setLength(0);
    }

    /**
     * Appends a line to the output buffer with proper newline handling.
     * Ensures consistent formatting by adding newlines between existing content.
     * 
     * @param line The text line to append to the output buffer
     */
    private void appendLine(String line) {
        boolean hasExistingContent = (output.length() > 0);
        if (hasExistingContent) {
            output.append(NEWLINE);
        }
        output.append(line);
    }

    /**
     * Displays the welcome message for the application.
     * Shows application name and initial greeting in a formatted box.
     */

    public void showWelcomeMessage() {
        appendLine(WELCOME_NAME);
        appendLine(WELCOME_PROMPT);
    }

    /**
     * Returns an empty command string for GUI mode.
     * GUI applications handle input through event handlers rather than command line reading.
     * 
     * @return Empty string as GUI doesn't use command line input
     */
    @Override
    public String readCommand() {
        return EMPTY_COMMAND;
    }

    /**
     * Displays a separator line for visual formatting.
     * Used to separate different sections of output.
     */
    @Override
    public void showLine() {
        appendLine(SEPARATOR_LINE);
    }

    /**
     * Displays the goodbye message when the application exits.
     * Shows farewell message with separator line for proper formatting.
     */
    @Override
    public void showGoodbye() {
        appendLine(GOODBYE_MESSAGE);
        appendLine(SEPARATOR_LINE);
    }

    /**
     * Displays an error message when task loading fails.
     * Informs the user that tasks could not be loaded from storage.
     */
    @Override
    public void showLoadingError() {
        appendLine(LOADING_ERROR_MESSAGE);
    }

    /**
     * Displays an error message to the user.
     * 
     * @param message The error message to display
     */
    @Override
    public void showError(String message) {
        // Assert precondition: error message should not be null
        assert message != null : "Error message should not be null";
        
        appendLine(message);
    }

    /**
     * Displays a general message to the user.
     * 
     * @param message The message to display
     */
    @Override
    public void showMessage(String message) {
        // Assert precondition: message should not be null
        assert message != null : "Message should not be null";
        
        appendLine(message);
    }

    /**
     * Displays the complete task list with numbered entries.
     * Shows either an empty list message or all tasks with 1-based numbering.
     * 
     * @param tasks The list of tasks to display
     */
    @Override
    public void printTaskList(ArrayList<Task> tasks) {
        // Assert precondition: task list should not be null
        assert tasks != null : "Task list should not be null";
        
        if (tasks.isEmpty()) {
            appendLine(EMPTY_TASK_LIST_MESSAGE);
            return;
        }
        
        appendLine(TASK_LIST_HEADER);
        displayNumberedTasks(tasks);
    }

    /**
     * Displays tasks that match search criteria with their original indices.
     * Shows either a no-matches message or the filtered tasks with original numbering.
     * 
     * @param matchingTasks The list of indexed tasks that match the search criteria
     */
    @Override
    public void printMatchingTasks(ArrayList<TaskList.IndexedTask> matchingTasks) {
        // Assert precondition: matching tasks list should not be null
        assert matchingTasks != null : "Matching tasks list should not be null";
        
        if (matchingTasks.isEmpty()) {
            appendLine(NO_MATCHES_MESSAGE);
            return;
        }
        
        appendLine(MATCHING_TASKS_HEADER);
        displayIndexedTasks(matchingTasks);
    }
    
    /**
     * Helper method to display a list of tasks with 1-based sequential numbering.
     * Extracts the common formatting logic for regular task lists.
     * 
     * @param tasks The tasks to display with sequential numbering
     */
    private void displayNumberedTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1; // Convert to 1-based numbering
            String taskLine = taskNumber + TASK_NUMBER_SEPARATOR + tasks.get(i).toString();
            appendLine(taskLine);
        }
    }
    
    /**
     * Helper method to display indexed tasks with their original indices.
     * Extracts the common formatting logic for search results.
     * 
     * @param indexedTasks The indexed tasks to display with original numbering
     */
    private void displayIndexedTasks(ArrayList<TaskList.IndexedTask> indexedTasks) {
        for (TaskList.IndexedTask indexedTask : indexedTasks) {
            int originalIndex = indexedTask.getIndex();
            String taskDescription = indexedTask.getTask().toString();
            String taskLine = originalIndex + TASK_NUMBER_SEPARATOR + taskDescription;
            appendLine(taskLine);
        }
    }
}
