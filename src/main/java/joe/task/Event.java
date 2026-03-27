package joe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Type of task that just contains a description, start date and end date
 */
public class Event extends Task {
    private String startDate;

    private static String formatDates(String from, String to) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedFrom = LocalDate.parse(from, inputFormat).format(outputFormat);
        String formattedTo = LocalDate.parse(to, inputFormat).format(outputFormat);

        return " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Creates an Event task that has not been completed.
     * 
     * @param description Description of event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, String from, String to) {
        super(description + formatDates(from, to));
        startDate = from;
    }

    /**
     * Creates an Event task whose completion status can be specified.
     * 
     * @param description Description of event.
     * @param from Start date of the event.
     * @param to End date of the event.
     * @param isDone Completion status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description + formatDates(from, to), isDone);
        startDate = from;
    }

    public String getNextDate() {
        return this.startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
