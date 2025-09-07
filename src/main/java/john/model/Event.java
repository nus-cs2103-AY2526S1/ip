package john.model;

import java.time.LocalDateTime;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatTime(from) + " to: " + formatTime(to) + ")";
    }

    @Override
    public String serialise() {
        return baseSerialize("E", formatTime(from), formatTime(to));
    }
}
