package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate start;
    protected LocalDate end;

    // Expect start and end as yyyy-MM-dd strings (consistent with Deadline)
    public Events(String description, String start, String end) {
        super(description);
        assert start != null && !start.trim().isEmpty() : "Start time must not be empty";
        assert end != null && !end.trim().isEmpty() : "End time must not be empty";
        // Parse using ISO date format yyyy-MM-dd
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public LocalDate getFrom() {
        return this.start;
    }

    public LocalDate getTo() {
        return this.end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedFrom = start.format(formatter);
        String formattedTo = end.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
