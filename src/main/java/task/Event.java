package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public final LocalDate from;
    public final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(dtf) + " to: " + to.format(dtf) + ")";
    }
}
