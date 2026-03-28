package tasks;
import java.time.LocalDate;

import exception.RomidasException;

/**
 * Represents a deadline task with a specific due date.
 * Extends the base Task class to provide deadline-specific functionality.
 */
public class DeadlineTask extends Task {
    // Constants for DeadlineTask formatting - eliminates magic strings
    private static final String DEADLINE_STATUS_ICON = "[D]";
    private static final String DEADLINE_TYPE_MARKER = "D";
    private static final String FIELD_SEPARATOR = " | ";
    private static final String COMPLETION_TRUE = "1";
    private static final String COMPLETION_FALSE = "0";
    private static final String BY_PREFIX = " (by: ";
    private static final String BY_SUFFIX = ")";
    private static final int EXPECTED_PARTS_COUNT = 3;
    private static final int COMPLETION_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int BY_MARKER_LENGTH = 6; // "(by: ".length()
    
    private final LocalDate deadline;

    /**
     * Constructs a new DeadlineTask with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date in yyyy-MM-dd format.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Gets the deadline date for this task.
     * 
     * @return The deadline as a LocalDate
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Converts a string array representation back to a DeadlineTask.
     * Parses the task data from storage format and reconstructs the DeadlineTask object.
     * Extracts the base description and deadline from the formatted string.
     *
     * @param parts Array containing task type, completion status, and formatted description.
     * @return The reconstructed DeadlineTask.
     * @throws RomidasException If the input format is invalid or missing required parts.
     */
    public static Task toTask(String[] parts) throws RomidasException {
        validatePartsArray(parts);
        
        String fullDescription = parts[DESCRIPTION_INDEX];
        String baseDescription = extractBaseDescription(fullDescription);
        String deadlineString = extractDeadlineString(fullDescription);
        
        DeadlineTask task = new DeadlineTask(baseDescription, deadlineString);
        
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
    
    /**
     * Extracts the base description from the full description string.
     * Separates the task description from deadline formatting.
     * 
     * @param fullDescription The full description containing deadline info
     * @return The base description without deadline formatting
     * @throws RomidasException if the format is invalid
     */
    private static String extractBaseDescription(String fullDescription) throws RomidasException {
        if (!fullDescription.contains(BY_PREFIX)) {
            throw new RomidasException("Invalid deadline format. Expected '(by: date)' but got: "
                    + fullDescription);
        }
        
        int byIndex = fullDescription.indexOf(BY_PREFIX);
        return fullDescription.substring(0, byIndex);
    }
    
    /**
     * Extracts the deadline string from the full description.
     * Parses the date portion from the formatted description.
     * 
     * @param fullDescription The full description containing deadline info
     * @return The deadline string
     * @throws RomidasException if the format is invalid
     */
    private static String extractDeadlineString(String fullDescription) throws RomidasException {
        int byIndex = fullDescription.indexOf(BY_PREFIX);
        int closingParen = fullDescription.indexOf(BY_SUFFIX, byIndex);
        
        if (closingParen == -1) {
            throw new RomidasException("Invalid deadline format. Expected closing ')' after "
                    + "'(by: date)' in: " + fullDescription);
        }
        
        return fullDescription.substring(byIndex + BY_MARKER_LENGTH, closingParen);
    }

    @Override
    public String toText() {
        String completionStatus = this.isDone ? COMPLETION_TRUE : COMPLETION_FALSE;
        return DEADLINE_TYPE_MARKER + FIELD_SEPARATOR + completionStatus + FIELD_SEPARATOR 
                + this.getDescription() + BY_PREFIX + deadline + BY_SUFFIX;
    }

    @Override
    public String getStatus() {
        return DEADLINE_STATUS_ICON;
    }

    /**
     * Returns a string representation of this deadline task including its status,
     * completion icon, description, and deadline date.
     *
     * @return The complete string representation of this deadline task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + SPACE_SEPARATOR + this.getDescription() 
                + BY_PREFIX + this.deadline + BY_SUFFIX;
    }
    
    /**
     * Checks if this deadline task is equal to another object.
     * Two deadline tasks are equal if they have the same description and deadline date.
     * 
     * @param obj The object to compare with
     * @return true if the deadline tasks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        
        DeadlineTask other = (DeadlineTask) obj;
        assert this.deadline != null : "Deadline should not be null";
        return this.deadline.equals(other.deadline);
    }
    
    /**
     * Returns the hash code for this deadline task.
     * Based on the description and deadline to ensure consistent hashing.
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        assert this.deadline != null : "Deadline should not be null";
        result = 31 * result + deadline.hashCode();
        return result;
    }
}
