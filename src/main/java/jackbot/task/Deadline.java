package jackbot.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// Deadline Task

public class Deadline extends Task {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime by;

    public Deadline(String description) {
        super(description.split(" \\/by ", 2)[0]);

        String[] parts = description.split(" \\/by ", 2);

        this.by = LocalDateTime.parse(parts[1], formatter);
    }

    // Used for deserialization
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialize() {
        return "D" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription() + "|" + this.by;
    }
}
