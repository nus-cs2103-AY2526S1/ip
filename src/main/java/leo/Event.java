package leo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);
    }

    /**
     * Returns string in the format in which the task will be printed on the user interface
     * @return A string formatted to be printed on the user interface
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: "
                 + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")");
    }

    /**
     * Converts the Event task into a format to be written into a file
     * Easier to parse from file back to task
     * @return String in the format that can be written into the file linked to storage
     */
    @Override
    public String toSaveFormat() {
        return String.join(" | ", "E", (isDone ? "1" : "0"), description,
                "from=" + start, "to=" + end);
    }

    @Override
    public boolean isUpcoming(LocalDate now, LocalDate max) {
        return !this.endDate.isBefore(now) && !this.startDate.isAfter(max);
    }
}
