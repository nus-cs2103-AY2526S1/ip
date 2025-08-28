package evansbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected LocalDate byDate;
    protected String byRaw;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
            this.byRaw = by;
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.byRaw = by;
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.byDate = LocalDate.parse(by);
            this.byRaw = by;
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.byRaw = by;
        }
    }

    public LocalDate getByDate() {
        return this.byDate;
    }

    @Override
    public String toString() {
        String date;
        if (byDate != null) {
            date = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            date = byRaw;
        }
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
