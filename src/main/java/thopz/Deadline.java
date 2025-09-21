package thopz;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline task
 * A deadline task has a description and a due date/time
 */

public class Deadline extends Task {
    public static final DateTimeFormatter[] INPUT_FORMATS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/dd/MM HH:mm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"),
    };
    public static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private final String due;

    /**
     * Constructs a command to add deadline with the specified description and due date.
     *
     * @param tasks  the description of the task
     * @param by the string representation of the due date/time
     *
     */

    public Deadline(String tasks, String by) {
        super(tasks);
        this.due = parseDate(by.trim());
        if (this.due == null) {
            throw new IllegalArgumentException("give in date and time format");
        }
    }

    /**
     * Parses the given string into list of formatted date/time using the accepted input formats.
     * If parsing fails,returns the original string.
     *
     * @param dateString  string to parse
     * @return  formatted date/time string
     */

    public String parseDate(String dateString) {
        for (DateTimeFormatter formatter : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(dateString, formatter).format(OUTPUT);
            } catch (DateTimeException e) {
                System.out.println(" ");
            }
        }
        return dateString;
    }


    /**
     * Saving format for storage class
     */


    @Override
    public String saveformat() {
        return "D / " + (getTaskStatus() ? "1" : "0") + " / " + getTasks() + " / " + due;
    }

    /**
     * String representation of the deadline
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due by " + due + ")";
    }
}
