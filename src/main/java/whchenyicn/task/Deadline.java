package whchenyicn.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A Deadline Task with a date representing deadline.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of the tasks.
     * @param by When the task must be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseToLocalDate(by);
    }

    /**
     * Parses String into LocalDate.
     *
     * @param s Input string.
     * @return Parsed LocalDate.
     */
    private LocalDate parseToLocalDate(String s) {
        s = s.trim();
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            System.out.println("Error Parsing: " + e.getMessage());
        }

        throw new IllegalArgumentException("Invalid Date Format");
    }

    @Override
    public String toSave() {
        return String.format("D | %d | %s | %s", getDoneFlag(), getDesc(), by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public LocalDate getDate() {
        return this.by;
    }
}