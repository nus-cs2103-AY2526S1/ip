package evansbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDate fromDate;
    protected String fromRaw;
    protected LocalDate toDate;
    protected String toRaw;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from);
            this.fromRaw = from;
            this.toDate = LocalDate.parse(to);
            this.toRaw = to;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromRaw = from;
            this.toDate = null;
            this.toRaw = to;
        }
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            this.fromDate = LocalDate.parse(from);
            this.fromRaw = from;
            this.toDate = LocalDate.parse(to);
            this.toRaw = to;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromRaw = from;
            this.toDate = null;
            this.toRaw = to;
        }
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        String from;
        String to;

        if (fromDate != null && toDate != null) {
            from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            from = fromRaw;
            to = toRaw;
        }
        return "[E]" + super.toString() + " (From: " + from + " to: " + to + ")";
    }
}
