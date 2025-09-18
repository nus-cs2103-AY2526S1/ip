package jackbot.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// Event Task

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Event(String description) {
        super(description.split(" \\/from ", 2)[0]);

        String[] parts = description.split(" \\/from ", 2);
        parts = parts[1].split(" \\/to ", 2);

        this.from = LocalDateTime.parse(parts[0], formatter);
        this.to = LocalDateTime.parse(parts[1], formatter);
    }

    // Used for deserialization
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String serialize() {
        return "E" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription() + "|" + this.from + "|" + this.to;
    }
}
