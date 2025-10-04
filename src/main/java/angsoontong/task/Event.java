package angsoontong.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate start;
    private LocalDate end;

    /**
     * constructor
     * @param name String name to describe task
     * @param start LocalDate indicating start date of event
     * @param end LocalDate indicating end date of event
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * custom toString representation for event
     */
    @Override
    public String toString() {
        return withTags(String.format("[E]" + super.toString() +
                "(from: " + start.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")"));
    }

    /**
     * method to format task description to write to storage file
     */
    @Override
    public String toFileFormat() {
        return "D | " +
                (super.isDone() ? "1" : "0") +
                " | " + super.getName() +
                " | " + this.start + " | " + this.end +
                tagsForFile();
    }
}
