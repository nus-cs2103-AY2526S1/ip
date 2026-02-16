package mon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a basic task with a name and completion status.
 * This is the base class for all task types in the Mon application.
 */
public class Task {
    private String taskName;
    private boolean status;

    // Date formatters for conversion
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter STANDARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a task with the specified name and status.
     * 
     * @param taskName The name of the task
     * @param status The completion status of the task
     */
    public Task(String taskName, boolean status) {
        assert taskName != null : "Task name cannot be null";
        assert !taskName.trim().isEmpty() : "Task name cannot be empty";
        this.taskName = taskName;
        this.status = status;
    }

    /**
     * Constructs a task with the specified name and default status (incomplete).
     * 
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this(taskName, false);
    }

    /**
     * Gets the name of the task.
     * 
     * @return The task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the completion status of the task.
     * 
     * @return true if the task is completed, false otherwise
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Sets the completion status of the task.
     * 
     * @param status true to mark as completed, false to mark as incomplete
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Converts a date from file format (MMM d yyyy) to standard format (yyyy-MM-dd).
     * 
     * @param fileFormatDate The date string in file format (e.g., "Dec 3 2017")
     * @return The date string in standard format (e.g., "2017-12-03")
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static String convertFileFormatToStandardDate(String fileFormatDate) {
        try {
            LocalDate date = LocalDate.parse(fileFormatDate, FILE_DATE_FORMATTER);
            return date.format(STANDARD_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format in file: '" + fileFormatDate + 
                "'. Expected format: MMM d yyyy (e.g., Dec 3 2017)");
        }
    }

    /**
     * Converts a date from standard format (yyyy-MM-dd) to file format (MMM d yyyy).
     * 
     * @param standardFormatDate The date string in standard format (e.g., "2017-12-03")
     * @return The date string in file format (e.g., "Dec 3 2017")
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static String convertStandardToFileFormatDate(String standardFormatDate) {
        try {
            LocalDate date = LocalDate.parse(standardFormatDate, STANDARD_DATE_FORMATTER);
            return date.format(FILE_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: '" + standardFormatDate + 
                "'. Expected format: yyyy-MM-dd (e.g., 2017-12-03)");
        }
    }

    /**
     * Creates a Task object from a string representation.
     * 
     * @param taskString The string representation of the task
     * @return A new Task object created from the string
     */
    public static Task toTask(String taskString) {
        assert taskString != null : "Task string cannot be null";
        String[] parts = taskString.split(" \\| ");
        assert parts.length >= 3 : "Invalid task string format: " + taskString;
        return new Task(parts[2], parts[1].equals("1"));
    }

    /**
     * Converts the task to its string representation for file storage.
     * 
     * @return A string representation suitable for saving to file
     */
    public String toFileString() {
        return (status ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        return "[" + (status ? "X" : " ") + "] " + taskName;
    }
}
