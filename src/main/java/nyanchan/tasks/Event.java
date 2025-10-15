package nyanchan.tasks;

import nyanchan.exceptions.IncorrectFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDate startDate;
    private LocalDate endDate;

    /** Formatter for parsing input dates (dd/MM/yyyy). */
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /** Formatter for displaying dates in a readable form. */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an Event task.
     *
     * @param description event description
     * @param from start date in dd/MM/yyyy format
     * @param to end date in dd/MM/yyyy format
     * @throws IncorrectFormatException if date format is invalid
     */
    public Event(String description, String from, String to) throws IncorrectFormatException {
        super(description);
        this.convertToDates(from, to);
    }

    /** @return formatted start date string */
    public String getFrom() {
        return this.from;
    }

    /** @return formatted end date string */
    public String getTo() {
        return this.to;
    }

    /** @return start date as LocalDate */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /** @return end date as LocalDate */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Converts and stores start/end dates from string input.
     *
     * @throws IncorrectFormatException if parsing fails
     */
    private void convertToDates(String from, String to) throws IncorrectFormatException {
        try {
            this.startDate = LocalDate.parse(from, INPUT_FORMAT);
            this.endDate = LocalDate.parse(to, INPUT_FORMAT);
            this.from = this.startDate.format(OUTPUT_FORMAT);
            this.to = this.endDate.format(OUTPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new IncorrectFormatException("HISS, invalid date format! Use dd/MM/yyyy!");
        }
    }

    /** @return string representation of the event */
    @Override
    public String toString() {
        String formattedFrom = this.startDate.format(OUTPUT_FORMAT);
        String formattedTo = this.endDate.format(OUTPUT_FORMAT);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
