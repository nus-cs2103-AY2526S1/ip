package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the Event is occuring at the inputDate(i.e. the inputDate 
     * is not earlier than {@code from}, and is not after than {@code to}). 
     */
    @Override
    public Boolean isOcurringAt(LocalDateTime inputDate) {
        return !(inputDate.isBefore(from) | inputDate.isAfter(to));
    }

    @Override
    public String getFormattedString() {
        return "E | " + super.getFormattedString() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), 
            from.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
            to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
