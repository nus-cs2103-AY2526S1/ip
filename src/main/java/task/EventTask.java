package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public EventTask(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public EventTask(String taskName, boolean isDone, LocalDate from, LocalDate to) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorage() {
        String doneMark = isDone ? "1" : "0";
        return "E | " + doneMark + " | " + taskName + " | " + from + " | " + to;
    }

    @Override
    public LocalDate getDate() {
        return from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                     + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                     + " to: "
                     + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}