package tasks;
import exception.RomidasException;

/**
 * Represents an event task with a start and end time.
 * Extends the base Task class to provide event-specific functionality.
 */
public class Event extends Task {
    String from;
    String to;

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
        if (parts.length != 4) {
            throw new RomidasException("Invalid number of arguments. Expected 4 but got " 
                    + parts.length);
        }
        String[] timeParts = parts[3].split("-");
        if (timeParts.length != 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
            throw new RomidasException("Invalid event format. Expected 'from-to' but got: " 
                    + parts[3]);
        }
        // Extract the base description by removing the "(from: ... to: ...)" part
        String baseDescription = parts[2];
        if (baseDescription.contains(" (from: ")) {
            baseDescription = baseDescription.substring(0, 
                    baseDescription.indexOf(" (from: "));
        }
        Event task = new Event(baseDescription, timeParts[0], timeParts[1]);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.getDescription() 
                + " (from: " + this.from + " to: " + this.to + ") | " 
                + this.from + "-" + this.to;
    }

    @Override
    public String getStatus() {
        return "[E]";
    }

    /**
     * Returns a string representation of this event task including its status,
     * completion icon, description, and time range.
     *
     * @return The complete string representation of this event task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + " " + this.getDescription() 
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}