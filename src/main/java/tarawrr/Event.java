package tarawrr;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event Class - Represents a Task with a description and start time and end time
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    //Constructor initiates an instance of Event with name, start time, and end time
    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        String startDate = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String endDate = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E] " +
                super.toString() +
                " (from: " +
                startDate +
                " to: " +
                endDate +
                ")";
    }

    @Override
    public String toStorageString() {
        String startDate = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String endDate = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("E | %d | %s | %s", super.isDone() ? 1 : 0, super.getDescription(),
                startDate + " to " + endDate);
    }

    public Event changeDate(String startDate, String endDate) {
        return new Event(this.getDescription(), startDate, endDate);
    }

}
