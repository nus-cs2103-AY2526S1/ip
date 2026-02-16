package dukeychatbot.tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Constructs Event class which inherits from Task class.
 * Overrides {@code toString()} method.
 */
public class Event extends Task {

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     *
     * Event {@code toString()} method includes "[E]" at the front of the string, and
     * the event's timeframe.
     */
    @Override
    public String toString() {
        String currentDescription = this.getDescription();
        String formattedDescription;
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";

        String[] splitDescription = currentDescription.split("/from");
        String[] furtherSplitDescription = splitDescription[1].split("/to");
        String fromDate = furtherSplitDescription[0].trim();
        String toDate = furtherSplitDescription[1].trim();
        if (fromDate.length() == 10 && fromDate.matches(datePattern)) {
            try {
                LocalDate dateInput = LocalDate.parse(fromDate);
                fromDate = dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Date parsed failed. Error message: " + e.getMessage());
            }
        }
        if (toDate.length() == 10 && toDate.matches(datePattern)) {
            try {
                LocalDate dateInput = LocalDate.parse(toDate);
                toDate = dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Date parsed failed. Error message: " + e.getMessage());
            }
        }
        formattedDescription = splitDescription[0] + "(from: " + fromDate + " to: " + toDate + ")";

        return "[E] " + "[" + this.getStatusIcon() + "] " + formattedDescription;
    }
}
