package tasks;

import exception.RainyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws RainyException {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };

        LocalDateTime parsedDate = null;
        for (DateTimeFormatter f : formatters) {
            try {
                parsedDate = LocalDateTime.parse(by.trim(), f);
                break;
            } catch (Exception ignored) {}
        }

        if (parsedDate == null) {
            throw new RainyException(
                    "oh no!!! wrong date format... please use yyyy-MM-dd HHmm "
                            + "or d/M/yyyy HHmm.");
        }
        this.by = parsedDate;
    }

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
