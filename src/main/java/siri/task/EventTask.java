package siri.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class for EventTask
 */
public class EventTask extends Task {
    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructor of EventTask
     * @param description description of the task
     * @param from the starting point
     * @param to the ending point
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        LocalDate parsed = null;
        try {
            parsed = LocalDate.parse(from);
        } catch (Exception e) {
        }
        this.fromDate = parsed;
        parsed = null;
        try {
            parsed = LocalDate.parse(to);
        } catch (Exception e) {
        }
        this.toDate = parsed;
    }

    /**
     * Returns the string representation of this event task,
     * including its type marker "[E]", completion status,
     * description, start time, and end time.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the event task
     *         (e.g., "[E][ ] project meeting (from: Mon 2pm to: 4pm)")
     */
    @Override
    public String display() {
        String fromDay;
        String toDay;
        if (fromDate != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
            fromDay = fromDate.format(dateTimeFormatter);
        } else {
            fromDay = from;
        }
        if (toDate != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
            toDay = toDate.format(dateTimeFormatter);
        } else {
            toDay = to;
        }
        if (super.isDone()) {
            return "[E][X] " + super.getDescription() + " (from: " + fromDay + " to: " + toDay +")";
        } else {
            return "[E][ ] " + super.getDescription() + " (from: " + fromDay + " to: " + toDay +")";
        }
    }

    /**
     * Getter of from
     * @return from
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Getter of to
     * @return to
     */
    public String getTo() {
        return this.to;
    }
}
