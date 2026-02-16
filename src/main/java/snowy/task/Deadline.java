package snowy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific due date.
 */
public class Deadline extends Task {
    private LocalDate dateTime;

    public Deadline(String task, String time) {
        super(task);
        this.dateTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    public Deadline(String task, boolean isDone, String time) {
        super(task, isDone);
        this.dateTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" +
            (this.checkStatus() ? "[X] " : "[ ] ") +
            super.toString() + 
            "(" +
            this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
            ")";
    }
}
