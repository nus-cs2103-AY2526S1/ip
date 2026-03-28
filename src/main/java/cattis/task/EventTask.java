package cattis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cattis.exception.CattisException;
import cattis.exception.CattisInvalidTimeException;

/**
 * Represents a task with <code>startTime</code> and <code>endTime</code>.
 */
public class EventTask extends Task {
    public static final String ICON = "[E]";
    private static final String PREFIX_START_TIME = "/from";
    private static final String PREFIX_END_TIME = "/to";
    private static final String NO_START_TIME_MSG = "[No start time]";
    private static final String NO_END_TIME_MSG = "[No end time]";

    private LocalDate startTime;
    private LocalDate endTime;

    EventTask(String taskName, String startTime, String endTime) throws CattisException {
        super(taskName);
        setTime(startTime, endTime);
    }

    /**
     * Constructor for {@code EventTask} with specific status
     * primarily used for loading tasks from the file
     * @param taskName task name
     * @param status mark or unmark
     */
    EventTask(String taskName, String startTime, String endTime, boolean status) throws CattisException {
        super(taskName);
        setTime(startTime, endTime);
        if (status) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    /**
     * Create an <code>EventTask</code> from the user-provided input (without keyword <code>event</code>)
     * <p>
     * The expected format is
     * <pre>
     * [taskName] /from [start time] /to [end time]
     * </pre>
     * Both start time and end time must be in the format of <code>YYYY-MM-DD</code> (e.g. 2020-11-11).
     *
     * @param prompt user prompt without command keyword <code>event</code>
     * @return <code>EventTask</code> instance
     * @throws CattisException for parsing error
     */
    public static EventTask createFromPrompt(String prompt) throws CattisException {
        String taskDescription;
        String startTime;
        String endTime;
        String[] parts = prompt.split(PREFIX_START_TIME, 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_EVENT);
        }
        taskDescription = parts[0].trim();
        parts = parts[1].trim().split(PREFIX_END_TIME, 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_EVENT);
        }
        startTime = parts[0].trim();
        endTime = parts[1].trim();
        if (taskDescription.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new CattisException(CattisException.EMPTY_FIELD);
        }
        return new EventTask(taskDescription, startTime, endTime);
    }

    @Override
    public String toEncodedString() {
        return ICON + Task.SPLITTER
                + super.getStatusIcon()
                + Task.SPLITTER + super.getTaskName() + Task.SPLITTER
                + encodeStartTime() + Task.SPLITTER
                + encodeEndTime();
    }

    @Override
    public String toString() {
        return ICON + super.toString() + String.format(
                " (from: %s to: %s)", getStartTime(), getEndTime()
        );
    }

    /**
     * Sets the start time and end time for the task with input validation.
     * <p>
     * This includes checking the format of the input strings and ensuring that
     * the start time occurs before the end time.
     *
     * @param startTime the start time string to be validated and set
     * @param endTime   the end time string to be validated and set
     * @throws CattisException if the format is invalid or the start time is not before the end time
     */
    public void setTime(String startTime, String endTime) throws CattisException {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
            this.startTime = LocalDate.parse(startTime, formatter);
            this.endTime = LocalDate.parse(endTime, formatter);
            if (this.endTime.isBefore(this.startTime)) {
                throw new CattisException("Start time must be before end time");
            }
            if (this.startTime.isBefore(LocalDate.now())) {
                throw new CattisException("Start time must not be in the past");
            }
            assert this.startTime != null && this.endTime != null;
        } catch (DateTimeParseException err) {
            throw new CattisInvalidTimeException(DATE_TIME_INPUT_FORMATTER);
        }
    }

    public String getStartTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.startTime == null
                ? NO_START_TIME_MSG
                : this.startTime.format(formatter);
    }

    public boolean isEqualDate(LocalDate date) {
        return this.startTime.equals(date) || this.endTime.equals(date);
    }

    /**
     * Format {@code this.startTime} as string based on input formatter
     * @return encoded deadline
     */
    public String encodeStartTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.startTime == null
                ? NO_START_TIME_MSG
                : this.startTime.format(formatter);
    }

    /**
     * Format {@code this.endTime} as string based on input formatter
     * @return encoded deadline
     */
    public String encodeEndTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.endTime == null
                ? NO_END_TIME_MSG
                : this.endTime.format(formatter);
    }

    public String getEndTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.endTime == null
                ? NO_END_TIME_MSG
                : this.endTime.format(formatter);
    }
}

