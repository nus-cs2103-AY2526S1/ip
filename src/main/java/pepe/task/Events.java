package pepe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pepe.exception.PepeExceptions;

/**
 * Represents an Event task.
 * <p>
 * An Event task has a name, a marked status, a start date, and an end date.
 * The end date cannot be before today, and the start date cannot be after the end date.
 */
public class Events extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs a new Event task with the given name, start date, and end date.
     *
     * @param name      the name or description of the task
     * @param startTime the start date in the format yyyy-MM-dd
     * @param endTime   the end date in the format yyyy-MM-dd
     * @throws PepeExceptions if the dates are invalid (format, end before today, or start after end)
     */
    public Events(String name, String startTime, String endTime) throws PepeExceptions {
        super(name);
        assert name != null && !name.isBlank() : "Event name should not be null or empty";
        String[] dates = validateStartDate(startTime, endTime);
        this.startTime = dates[0];
        this.endTime = dates[1];
    }
    /**
     * Validates and formats a start and end date for a task.
     * <p>
     * This method checks that both {@code startTime} and {@code endTime} are
     * non-null and non-blank. It then parses them as {@link java.time.LocalDate}
     * objects and enforces the following constraints:
     * <ul>
     *     <li>The end date cannot be before today.</li>
     *     <li>The start date cannot be after the end date.</li>
     * </ul>
     * If the dates are valid, they are formatted into the pattern "MMM d yyyy"
     * and returned as a two-element string array, with the start date at index 0
     * and the end date at index 1.
     *
     * @param startTime the start date as a string in "yyyy-MM-dd" format
     * @param endTime   the end date as a string in "yyyy-MM-dd" format
     * @return a string array containing the formatted start and end dates
     * @throws PepeExceptions if the input strings are in an invalid format, null,
     *                        blank, or violate the date constraints
     */
    public String[] validateStartDate(String startTime, String endTime) throws PepeExceptions {
        try {
            assert startTime != null && !startTime.isBlank() : "Start time should not be null or empty";
            assert endTime != null && !endTime.isBlank() : "End time should not be null or empty";
            String[] result = new String[2];
            LocalDate parsedStartTime = LocalDate.parse(startTime);
            LocalDate parsedEndTime = LocalDate.parse(endTime);
            if (parsedEndTime.isBefore(LocalDate.now())) {
                throw new PepeExceptions("Invalid Input: End Date cannot be before today");
            }
            if (parsedStartTime.isAfter(parsedEndTime)) {
                throw new PepeExceptions("Invalid Input: Start Date cannot be after End Date");
            } else {
                String formattedStartTime = parsedStartTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                String formattedEndTime = parsedEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                result[0] = formattedStartTime;
                result[1] = formattedEndTime;
                return result;
            }
        } catch (DateTimeParseException e) {
            throw new PepeExceptions("Invalid Input: Please check the format of your dates (yyyy-mm-dd)");
        }
    }
    /**
     * Returns a string representation of the Event task for display.
     * <p>
     * Format: [E][X] taskName (from: MMM d yyyy to: MMM d yyyy) if marked,
     * [E][ ] taskName (from: MMM d yyyy to: MMM d yyyy) if unmarked.
     *
     * @return a human-readable string representing the Event task
     */
    @Override
    public String toString() {
        assert startTime != null && !startTime.isBlank() : "Start time should be non-null and non-empty for display";
        assert endTime != null && !endTime.isBlank() : "End time should be non-null and non-empty for display";
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
    /**
     * Returns a string representing the Event task in a file-friendly format.
     * <p>
     * Format: E | 1 | taskName | MMM d yyyy - MMM d yyyy (if marked) or
     * E | 0 | taskName | MMM d yyyy - MMM d yyyy (if unmarked)
     *
     * @return the Event task formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        assert startTime != null && !startTime.isBlank()
                : "Start time should be non-null and non-empty for file format";
        assert endTime != null && !endTime.isBlank()
                : "End time should be non-null and non-empty for file format";
        return "E" + " | " + super.checkMarked() + " | "
                + super.getName()
                + " | "
                + this.startTime
                + " - " + this.endTime;
    }
}
