package Meal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Event task
 * An event task has a task name, start date, and end date
 */
public class Event extends Task {
    LocalDate startTime;
    LocalDate endTime;
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructor for event
     * @param taskName name of event
     * @param startTime start time of event
     * @param endTime end time of event
     */
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    /**
     * Returns start date of event
     */
    public LocalDate getStartTime() {
        return this.startTime;
    }

    /**
     * Returns end date of event
     */
    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Returns string representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(PRETTY) + " to: "
                + this.endTime.format(PRETTY) + ")";
    }
}
