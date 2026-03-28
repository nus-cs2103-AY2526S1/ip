package task;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents an event task with a start and end date.
 * <p>
 * This class extends the <code>Task</code> class and implements the <code>GetDateable</code> interface.
 * It is used to represent tasks that occur within a specific date range.
 * </p>
 */
public class EventTask extends Task implements GetDateable {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new EventTask with the specified name, start date, and end date.
     *
     * @param name the name of the event task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public EventTask(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new EventTask with the specified name, completion status, start date, and end date.
     *
     * @param name the name of the event task
     * @param isCompleted the completion status of the event task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public EventTask(String name, boolean isCompleted, LocalDate from, LocalDate to) {
        super(name, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public LocalDate getDate() {
        return from;
    }

    @Override
    public String save() {
        assert(from != null);
        assert(to != null);
        return super.save() + "E#" + this.from + "#" + this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public boolean isUpcoming(ChronoLocalDate today) {
        return from.isAfter(today);
    }
}
