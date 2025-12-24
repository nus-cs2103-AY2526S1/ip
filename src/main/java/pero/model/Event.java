package pero.model;

import pero.exception.PeroException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents event task with starting to end date and timing.
 * An event has a description, completion status, and start and end date+time.
 */
public class Event extends Task {

    /** Date+time that task starts */
    protected LocalDateTime fromTimeObj;
    /** Date+time that task ends */
    protected LocalDateTime toTimeObj;

    private static final String COMMAND_KEYWORD = "event";
    private static final String FROM_SEPARATOR = " /from ";
    private static final String TO_SEPARATOR = " /to ";
    private static final String WRONG_FORMAT_EXCEPTION =
            "Oops! Event requires 'event [task] /from [YYYY-DD-MM HHmm] /to [YYYY-DD-MM HHmm]' format, try again!";

    private static final String DATE_TIME_PATTERN = "yyyy-dd-MM HHmm";
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter FINAL_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");


    public Event(String description, boolean isDone, LocalDateTime fromTimeObj, LocalDateTime toTimeObj){
        super(description, isDone);
        this.fromTimeObj = fromTimeObj;
        this.toTimeObj = toTimeObj;
    }

    /**
     * Returns new Event object.
     *
     * @param input User input line.
     * @return New Event task according to input.
     * @throws PeroException If input is in wrong format.
     */
    public static Event fromInput(String input) throws PeroException {
        if (input.equals(COMMAND_KEYWORD) || !input.contains(FROM_SEPARATOR) || !input.contains(TO_SEPARATOR)) {
            throw new PeroException(WRONG_FORMAT_EXCEPTION);
        }

        //starts at index 6, remove "event "
        String[] taskEvent = input.substring(COMMAND_KEYWORD.length() + 1).split(FROM_SEPARATOR);
        if (taskEvent.length < 2) {
            throw new PeroException(WRONG_FORMAT_EXCEPTION);
        }
        String task = taskEvent[0];
        String[] time = taskEvent[1].split(" /to ");
        if (time.length < 2) {
            throw new PeroException(WRONG_FORMAT_EXCEPTION);
        }

        String from = time[0].trim();
        LocalDateTime fromTimeObj = parseDateTime(from);

        String to = time[1].trim();
        LocalDateTime toTimeObj = parseDateTime(to);

        return new Event(task,false,fromTimeObj,toTimeObj);
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone? "1" : "0") +
                " | " + this.description + " | "
                + this.fromTimeObj.format(STORAGE_FORMATTER) + " | "
                + this.toTimeObj.format(STORAGE_FORMATTER);
    }

    @Override
    public LocalDate getDueDate() {
        return fromTimeObj.toLocalDate(); // extracts local date portion
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.fromTimeObj.format(FINAL_DISPLAY_FORMATTER) +
                " to: " + this.toTimeObj.format(FINAL_DISPLAY_FORMATTER) + ")";
    }


}
