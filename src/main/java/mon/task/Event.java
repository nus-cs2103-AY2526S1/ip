package mon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates a new event task with the specified name, status, start time, and end time.
     * 
     * @param taskName the name of the task
     * @param status the completion status of the task
     * @param startTime the start date in yyyy-MM-dd format
     * @param endTime the end date in yyyy-MM-dd format
     * @throws IllegalArgumentException if the date format is invalid
     */
    public Event(String taskName, boolean status, String startTime, String endTime) {
        super(taskName, status);
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    /**
     * Creates a new incomplete event task with the specified name, start time, and end time.
     * 
     * @param taskName the name of the task
     * @param startTime the start date in yyyy-MM-dd format
     * @param endTime the end date in yyyy-MM-dd format
     */
    public Event(String taskName, String startTime, String endTime) {
        this(taskName, false, startTime, endTime);
    }

    /**
     * Returns the start date of this event.
     * 
     * @return the start date
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Returns the end date of this event.
     * 
     * @return the end date
     */
    public LocalDate getEndTime() {
        return endTime;
    }

    /**
     * Creates an event task from a string representation.
     * 
     * @param taskString the string representation of the event task
     * @return the event task object
     * @throws IllegalArgumentException if the task format is invalid
     */
    public static Event toEventTask(String taskString) {
        try {
            String[] parts = taskString.split(" \\| ", 5);
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid event format in file");
            }
            // Convert the file format dates to standard format using the helper function
            String standardStartDate = convertFileFormatToStandardDate(parts[3]);
            String standardEndDate = convertFileFormatToStandardDate(parts[4]);
            return new Event(parts[2], parts[1].equals("1"), standardStartDate, standardEndDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing event from file: " + e.getMessage());
        }
    }

    @Override
    public String toFileString() {
        String standardStartDate = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String standardEndDate = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fileStartDate = convertStandardToFileFormatDate(standardStartDate);
        String fileEndDate = convertStandardToFileFormatDate(standardEndDate);
        return "E | " + super.toFileString() + " | " + fileStartDate + " | " + fileEndDate;
    }

    @Override
    public String toString() {
        String standardStartDate = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String standardEndDate = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String displayStartDate = convertStandardToFileFormatDate(standardStartDate);
        String displayEndDate = convertStandardToFileFormatDate(standardEndDate);
        return "[E]" + super.toString() + " (from: " + displayStartDate + " to: " + displayEndDate + ")";
    }
}
