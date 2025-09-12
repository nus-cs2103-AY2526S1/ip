package tasks;
import exception.RomidasException;

/**
 * Represents an event task with a start and end time.
 * Extends the base Task class to provide event-specific functionality.
 */
public class Event extends Task {
    // Constants for Event formatting - eliminates magic strings
    private static final String EVENT_STATUS_ICON = "[E]";
    private static final String EVENT_TYPE_MARKER = "E";
    private static final String FIELD_SEPARATOR = " | ";
    private static final String COMPLETION_TRUE = "1";
    private static final String COMPLETION_FALSE = "0";
    private static final String FROM_PREFIX = " (from: ";
    private static final String TO_PREFIX = " to: ";
    private static final String TIME_SUFFIX = ")";
    private static final String TIME_SEPARATOR = "-";
    private static final int EXPECTED_PARTS_COUNT = 4;
    private static final int COMPLETION_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int TIME_RANGE_INDEX = 3;
    private static final int EXPECTED_TIME_PARTS = 2;
    private static final int FROM_TIME_INDEX = 0;
    private static final int TO_TIME_INDEX = 1;
    
    private final String from;
    private final String to;

    /**
     * Constructs a new Event task with the specified description and time range.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts a string array representation back to an Event task.
     * Parses the task data from storage format and reconstructs the Event object.
     * Handles validation of input format and extracts time information.
     *
     * @param parts Array containing task type, completion status, description, and time range.
     * @return The reconstructed Event task.
     * @throws RomidasException If the input format is invalid or missing required parts.
     */
    public static Task toTask(String[] parts) throws RomidasException {
        validatePartsArray(parts);
        
        String[] timeParts = parseTimeRange(parts[TIME_RANGE_INDEX]);
        String baseDescription = extractBaseDescription(parts[DESCRIPTION_INDEX]);
        
        Event task = new Event(baseDescription, timeParts[FROM_TIME_INDEX], timeParts[TO_TIME_INDEX]);
        
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
     * Parses the time range string into from and to components.
     * Validates the format and extracts time boundaries.
     * 
     * @param timeRange The time range string in format "from-to"
     * @return Array containing from and to times
     * @throws RomidasException if the format is invalid
     */
    private static String[] parseTimeRange(String timeRange) throws RomidasException {
        String[] timeParts = timeRange.split(TIME_SEPARATOR);
        
        if (timeParts.length != EXPECTED_TIME_PARTS || timeParts[FROM_TIME_INDEX].isBlank() 
                || timeParts[TO_TIME_INDEX].isBlank()) {
            throw new RomidasException("Invalid event format. Expected 'from-to' but got: " + timeRange);
        }
        
        return timeParts;
    }
    
    /**
     * Extracts the base description by removing time formatting.
     * Separates the event description from time information.
     * 
     * @param fullDescription The full description containing time info
     * @return The base description without time formatting
     */
    private static String extractBaseDescription(String fullDescription) {
        if (fullDescription.contains(FROM_PREFIX)) {
            return fullDescription.substring(0, fullDescription.indexOf(FROM_PREFIX));
        }
        return fullDescription;
    }

    @Override
    public String toText() {
        String completionStatus = this.isDone ? COMPLETION_TRUE : COMPLETION_FALSE;
        return EVENT_TYPE_MARKER + FIELD_SEPARATOR + completionStatus + FIELD_SEPARATOR 
                + this.getDescription() + FROM_PREFIX + this.from + TO_PREFIX + this.to + TIME_SUFFIX 
                + FIELD_SEPARATOR + this.from + TIME_SEPARATOR + this.to;
    }

    @Override
    public String getStatus() {
        return EVENT_STATUS_ICON;
    }

    /**
     * Returns a string representation of this event task including its status,
     * completion icon, description, and time range.
     *
     * @return The complete string representation of this event task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + SPACE_SEPARATOR + this.getDescription() 
                + FROM_PREFIX + this.from + TO_PREFIX + this.to + TIME_SUFFIX;
    }
    
    /**
     * Gets the start time of this event.
     * 
     * @return The start time string
     */
    public String getFrom() {
        return from;
    }
    
    /**
     * Gets the end time of this event.
     * 
     * @return The end time string
     */
    public String getTo() {
        return to;
    }
    
    /**
     * Checks if this event is equal to another object.
     * Two events are equal if they have the same description, start time, and end time.
     * 
     * @param obj The object to compare with
     * @return true if the events are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        
        if (!(obj instanceof Event)) {
            return false;
        }
        
        Event other = (Event) obj;
        return (from != null ? from.equals(other.from) : other.from == null) &&
               (to != null ? to.equals(other.to) : other.to == null);
    }
    
    /**
     * Returns the hash code for this event.
     * Based on the description, start time, and end time to ensure consistent hashing.
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}