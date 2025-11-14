package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end date/time (event).
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new Event task.
     *
     * @param input the description and time in the format
     *              "desc /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm"
     *              or with dates only "desc /from yyyy-MM-dd /to yyyy-MM-dd"
     */
    public Event(String input) {
        super("");
        String[] inputArr = input.split(" /from ", 2);
        this.description = inputArr[0];
        String temp = inputArr[1];
        String[] fromToArr = temp.split(" /to ", 2);

        String fromDateTimeStr = fromToArr[0];
        String toDateTimeStr = fromToArr[1];
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            this.from = LocalDateTime.parse(fromDateTimeStr, dateTimeFormat);
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(fromDateTimeStr, dateFormat);
            this.from = date.atStartOfDay();
        }

        try {
            this.to = LocalDateTime.parse(toDateTimeStr, dateTimeFormat);
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(toDateTimeStr, dateFormat);
            this.to = date.atStartOfDay();
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return formatted string showing description, start and end times
     */
    @Override
    public String toString() {
        DateTimeFormatter oFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString()
                + " (from: " + from.format(oFormat)
                + " to: " + to.format(oFormat) + ")";
    }
}
