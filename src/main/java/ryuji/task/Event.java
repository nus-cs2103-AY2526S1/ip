package ryuji.task;

import java.time.LocalDate;

/**
 * Represents an event task with a start and end date/time.
 * <p>Event tasks represent events that occur at a specific time range, with a start date/time and an end date/time.
 * This class handles parsing and formatting flexible date/time input, supporting formats such as "yyyy-MM-dd"
 * or "yyyy-MM-dd HHmm".</p>
 */
public class Event extends Task {

    /** Parsed start date-time, or null if parsing fails */
    private final LocalDate startParsed;

    /** Parsed end date-time, or null if parsing fails */
    private final LocalDate endParsed;

    /** Raw start date/time string, fallback if parsing fails */
    private final String startRaw;

    /** Raw end date/time string, fallback if parsing fails */
    private final String endRaw;

    /**
     * Constructs an Event task from the full command string.
     * <p>Example input: "event party /from 2019-12-02 1800 /to 2019-12-02 2000"</p>
     *
     * @param input the full input string including event description and times
     */
    public Event(String input) {
        super(input.split("/from", 2)[0]);
        String[] fromSplit = input.split("/from", 2);
        String[] toSplit = input.split("/to", 2);

        int endOfStartDate = fromSplit[1].indexOf("/");
        String unparsedStartDate = fromSplit[1].substring(0, endOfStartDate).trim();

        String unparsedEndDate = toSplit[1].trim();

        LocalDate detectedStartDateTime = DateTimeHandler.getDate(unparsedStartDate);
        LocalDate detectedEndDateTime = DateTimeHandler.getDate(unparsedStartDate);

        if (detectedStartDateTime != null) {
            startParsed = detectedStartDateTime;
            startRaw = null;
        } else {
            startParsed = null;
            startRaw = unparsedStartDate;
        }

        if (detectedEndDateTime != null) {
            endParsed = detectedEndDateTime;
            endRaw = null;
        } else {
            endParsed = null;
            endRaw = unparsedEndDate;
        }
    }

    /**
     * Constructs an Event task with a marked status from the full command string.
     * <p>This constructor allows you to set the task's status (marked or not) directly.</p>
     *
     * @param input    the full input string including event description and times
     * @param isMarked true if task is marked done, false otherwise
     */
    public Event(String input, boolean isMarked) {
        super(input.split("/from", 2)[0], isMarked);
        String[] fromSplit = input.split("/from", 2);
        String[] toSplit = input.split("/to", 2);

        int endOfStartDate = fromSplit[1].indexOf("/");
        String unparsedStartDate = fromSplit[1].substring(0, endOfStartDate).trim();

        String unparsedEndDate = toSplit[1].trim();

        LocalDate detectedStartDateTime = DateTimeHandler.getDate(unparsedStartDate);
        LocalDate detectedEndDateTime = DateTimeHandler.getDate(unparsedStartDate);

        if (detectedStartDateTime != null) {
            startParsed = detectedStartDateTime;
            startRaw = null;
        } else {
            startParsed = null;
            startRaw = unparsedStartDate;
        }

        if (detectedEndDateTime != null) {
            endParsed = detectedEndDateTime;
            endRaw = null;
        } else {
            endParsed = null;
            endRaw = unparsedEndDate;
        }
    }

    /**
     * Checks if the event has valid start and end date/time.
     * <p>This method checks if both the start and end times have been correctly parsed or provided as raw strings.
     * The event is considered valid if it has either valid parsed date/time or raw date/time for both start and end times.</p>
     *
     * @return true if both start and end times are valid (parsed or raw)
     */
    @Override
    boolean checkValid() {
        boolean isStartParsedDateTimePresent = this.startParsed != null;
        boolean isStartRawDateTimePresent = this.startRaw != null && !this.startRaw.isEmpty();

        boolean isStartPresent = isStartParsedDateTimePresent || isStartRawDateTimePresent;

        boolean isEndParsedDateTimePresent = this.endParsed != null;
        boolean isEndRawDateTimePresent = endRaw != null && !endRaw.isEmpty();

        boolean isEndPresent = isEndParsedDateTimePresent && isEndRawDateTimePresent;

        boolean isDateTimePresent = isStartPresent || isEndPresent;

        return isDateTimePresent;
    }

    /**
     * Returns a user-friendly string representation of the event.
     * <p>The task string will include the event's status, description, start date/time, and end date/time.</p>
     *
     * @return string formatted as "[E]<task info> (from: start to: end)"
     */
    @Override
    public String toString() {
        String startStr;
        if (startParsed != null) {
            startStr = DateTimeHandler.formatDetectedDate(startParsed);
        } else {
            startStr = startRaw;
        }

        String endStr;
        if (endParsed != null) {
            endStr = DateTimeHandler.formatDetectedDate(endParsed);
        } else {
            endStr = endRaw;
        }

        return "[E]" + super.toString() + " (from: " + startStr + " to: " + endStr + ")";
    }

    /**
     * Returns a CSV row string representing this event task.
     * <p>The format for the CSV representation is: "E | status | label | start date/time | end date/time".</p>
     *
     * @return CSV formatted string of this event task
     */
    @Override
    public String toCsvRow() {
        String status = getStatusIcon();

        String startStr;
        if (startParsed != null) {
            startStr = DateTimeHandler.formatDetectedDate(startParsed);
        } else {
            startStr = startRaw;
        }

        String endStr;
        if (endParsed != null) {
            endStr = DateTimeHandler.formatDetectedDate(endParsed);
        } else {
            endStr = endRaw;
        }

        return String.format("E," + status + "," + label + " /from " + startStr + " /to " + endStr);
    }
}
