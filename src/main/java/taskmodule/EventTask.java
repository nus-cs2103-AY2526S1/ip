package taskmodule;

import java.time.LocalDate;

public class EventTask extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toDataString() {
        return String.format("E | %s | %s | %s", super.toDataString(), this.from, this.to);
    }
}
