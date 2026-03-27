package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task that takes place from a given time to another.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event.
     * @param description Task description.
     * @param from Time at which the Event begins.
     * @param to Time at which the Event ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        super.id = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveDesc() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")
                .withLocale(Locale.ENGLISH);
        return super.saveDesc() + " | " + from.format(outputFormatter) + " | " + to.format(outputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return super.showPriority() + "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: "
                + to.format(outputFormatter) + ")";
    }
}
