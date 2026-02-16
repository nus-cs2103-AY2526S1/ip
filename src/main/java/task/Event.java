package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents an Event task that has a name, a start date, and a end date */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Event Constructor
     *
     * @param name
     *            The name for this task
     *
     * @param from
     *            A {@link LocalDate} object representing the start date
     *
     * @param to
     *            A {@link LocalDate} object representing the end date
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskTitle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("%s (from: %s, to: %s)", getName(), from.format(formatter), to.format(formatter));
    }

    @Override
    protected char typeChar() {
        return 'E';
    }
}
