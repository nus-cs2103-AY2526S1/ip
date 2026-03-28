package tasks;
import exception.RomidasException;

/**
 * Represents a simple todo task without any time constraints.
 * Extends the base Task class to provide todo-specific functionality.
 */
public class TodoTask extends Task {
    // Constants for TodoTask formatting - eliminates magic strings
    private static final String TODO_STATUS_ICON = "[T]";
    private static final String TODO_TYPE_MARKER = "T";
    private static final String FIELD_SEPARATOR = " | ";
    private static final String COMPLETION_TRUE = "1";
    private static final String COMPLETION_FALSE = "0";
    private static final int EXPECTED_PARTS_COUNT = 3;
    private static final int COMPLETION_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    /**
     * Constructs a new TodoTask with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Converts a string array representation back to a TodoTask.
     * Parses the task data from storage format and reconstructs the TodoTask object.
     * Validates the input format and sets the completion status.
     *
     * @param parts Array containing task type, completion status, and description.
     * @return The reconstructed TodoTask.
     * @throws RomidasException If the input format is invalid or missing required parts.
     */
    public static Task toTask(String[] parts) throws RomidasException {
        validatePartsArray(parts);
        
        TodoTask task = new TodoTask(parts[DESCRIPTION_INDEX]);
        
        boolean isCompleted = parts[COMPLETION_INDEX].equals(COMPLETION_TRUE);
        task.setIsDone(isCompleted);
        
        return task;
    }
    
    /**
     * Validates the parts array for correct format.
     * Applies defensive programming to prevent invalid input.
     * 
     * @param parts The parts array to validate
     * @throws RomidasException if the format is invalid
     */
    private static void validatePartsArray(String[] parts) throws RomidasException {
        if (parts.length != EXPECTED_PARTS_COUNT) {
            throw new RomidasException("Invalid number of arguments. Expected " + EXPECTED_PARTS_COUNT 
                    + " but got " + parts.length);
        }
    }

    @Override
    public String toText() {
        String completionStatus = this.isDone ? COMPLETION_TRUE : COMPLETION_FALSE;
        return TODO_TYPE_MARKER + FIELD_SEPARATOR + completionStatus + FIELD_SEPARATOR + this.getDescription();
    }

    @Override
    public String getStatus() {
        return TODO_STATUS_ICON;
    }

}
