package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime dline;

    /**
     * Creates a new Deadline task.
     *
     * @param input the description and deadline in the format "desc /by yyyy-MM-dd HHmm" or "desc /by yyyy-MM-dd"
     */
    public Deadline(String input) {
        super("");
        String[] inputArr = input.split(" /by ", 2);
        this.description = inputArr[0];

        String dateTimeStr = inputArr[1];
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            this.dline = LocalDateTime.parse(dateTimeStr, dateTimeFormat);
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(dateTimeStr, dateFormat);
            this.dline = date.atStartOfDay();
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return formatted string showing description and deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter oFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + dline.format(oFormat) + ")";
    }
}
