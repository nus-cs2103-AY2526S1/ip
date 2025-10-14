package lux.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task representing an event with a start and end time.
 */
public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Create a new event task.
     *
     * @param description event description
     * @param from        start time
     * @param to          end time
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HHmm");
        return "[E] " + super.toString() + " (from: " + from.format(formatter) + " to: "
                + to.format(formatter) + ")";
    }
}
