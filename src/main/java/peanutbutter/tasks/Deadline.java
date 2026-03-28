package peanutbutter.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import peanutbutter.TaskType;


/**
 * Represents a deadline task with a due date or due date-time.
 * <p>
 * Provides methods to get the due time, display the task, and store it in a file-friendly format.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter inputDT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputDT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter inputD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputD = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDateTime byDT;
    private LocalDate byD;

    /**
     * Creates a new Deadline task with description and due date/time.
     *
     * @param description the description of the deadline
     * @param by the due date/time (yyyy-MM-dd or yyyy-MM-dd HHmm)
     * @throws IllegalArgumentException if the date/time format is invalid
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);

        try {
            byDT = LocalDateTime.parse(by, inputDT);
        } catch (DateTimeParseException e) {
            try {
                byDT = null;
                byD = LocalDate.parse(by, inputD);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date/time format. Use yyyy-MM-dd or yyyy-MM-dd HHmm");
            }
        }
    }

    /**
     * Returns the due time of the deadline as a LocalDateTime.
     * If only a date was provided, returns the start of that day.
     *
     * @return the due time of the deadline
     */
    public LocalDateTime getDueDateTime() {
        if (byDT != null) {
            return byDT;
        } else if (byD != null) {
            return byD.atStartOfDay();
        } else {
            return null;
        }
    }

    /**
     * Returns a string representation of the deadline for display.
     *
     * @return a formatted string including description and due time
     */
    @Override
    public String toString() {
        String byStr = byDT == null ? byD.format(outputD) : byDT.format(outputDT);
        return "[D] " + super.toString() + " (by: " + byStr + ")";
    }

    /**
     * Returns a string representation of the deadline suitable for storage in a file.
     *
     * @return a line-formatted string representing the deadline
     */
    @Override
    public String makePretty() {
        String byStr = byDT == null ? byD.format(inputD) : byDT.format(inputDT);
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byStr;
    }
}
