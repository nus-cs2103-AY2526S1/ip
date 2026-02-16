package ryuji.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 * <p>Deadline tasks have a specific due date and time, and this class handles parsing and formatting
 * flexible date/time input. The supported formats include "yyyy-MM-dd" and "yyyy-MM-dd HHmm".</p>
 * <p>For example, a deadline task can be created with input such as "deadline return book /by 2019-12-02 1800".</p>
 */
public class Deadline extends Task {

    /** Parsed date-time if input matches expected format; null otherwise. */
    private final LocalDate parsedDate;

    /** Raw date-time string used as fallback if parsing fails or no standard format. */
    private final String rawDateTime;

    /**
     * Constructs a Deadline task from a command string.
     * <p>This constructor splits the input string by the "/by" keyword, with the first part representing the task label
     * and the second part representing the date/time of the deadline.</p>
     * <p>If the date/time format is valid, it is parsed and stored. Otherwise, the raw date/time string is stored.</p>
     *
     * @param input the full command string including the deadline date/time (e.g., "deadline return book /by 2019-12-02 1800")
     */
    public Deadline(String input) {
        super(input.split("/by", 2)[0]);
        String[] parts = input.split("/by", 2);
        String dateString = parts[1].trim();

        LocalDate detectedDate = DateTimeHandler.getDate(dateString);

        if (detectedDate != null) {
            parsedDate = detectedDate;
            rawDateTime = null;
        } else {
            parsedDate = null;
            rawDateTime = dateString;
        }
    }

    /**
     * Constructs a Deadline task with a marked status from a command string.
     * <p>This constructor is similar to the previous one, but it also allows setting the task's completion status directly.</p>
     *
     * @param input    the full command string including the deadline date/time (e.g., "deadline return book /by 2019-12-02 1800")
     * @param isMarked true if the task is marked as done, false otherwise
     */
    public Deadline(String input, boolean isMarked) {
        super(input.split("/by", 2)[0], isMarked);
        String[] parts = input.split("/by", 2);
        String dateString = parts[1].trim();

        LocalDate detectedDate = DateTimeHandler.getDate(dateString);

        if (detectedDate != null) {
            parsedDate = detectedDate;
            rawDateTime = null;
        } else {
            parsedDate = null;
            rawDateTime = dateString;
        }
    }

    /**
     * Checks if this deadline task has a valid date/time.
     * <p>The task is considered valid if it has either a parsed date or a non-empty raw date string.</p>
     *
     * @return true if the deadline has a valid parsed date or a non-empty raw date string, false otherwise
     */
    @Override
    boolean checkValid() {
        boolean isParsedDateTimePresent = this.parsedDate != null;
        boolean isRawDateTimePresent = this.rawDateTime != null && !this.rawDateTime.isEmpty();

        boolean areBothTimesPresent = isParsedDateTimePresent || isRawDateTimePresent;

        return areBothTimesPresent;
    }

    /**
     * Returns a CSV row string representing this task.
     * <p>The format for the CSV representation is: "D | status | label | date/time".</p>
     * <p>If the task has a parsed date, it will be used in the CSV row; otherwise, the raw date/time string is used.</p>
     *
     * @return a CSV-formatted string representing this deadline task
     */
    @Override
    public String toCsvRow() {
        String status = getStatusIcon();
        String labelPart = this.label.split("/by", 2)[0].trim();
        String dateStr;

        boolean isDateTimePresent = parsedDate != null;

        if (isDateTimePresent)  {
            dateStr = DateTimeHandler.formatDetectedDate(parsedDate);
        } else {
            dateStr = rawDateTime;
        }

        return String.format("D," + status + "," + labelPart + " /by " + dateStr);
    }

    /**
     * Returns a user-friendly string representation of this deadline task.
     * <p>The task string will include the task's status, description, and the deadline date/time.</p>
     * <p>If a valid parsed date is available, it will be formatted and shown in the output. Otherwise, the raw date/time string is shown.</p>
     *
     * @return a formatted string including the task status, description, and the deadline date/time
     */
    @Override
    public String toString() {
        if (parsedDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + DateTimeHandler.formatDetectedDate(parsedDate) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + rawDateTime + ")";
        }
    }
}
