package alice.task;

import alice.Task;
import alice.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends DateTask {

    private LocalDateTime by;

    public Deadline(String description, String by) throws AliceException {
        super(description);
        this.by = parseDate(by);
    }

    public Deadline(String description, boolean isDone, String by) throws AliceException {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Please use format: dd/MM/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    public void setBy(String by) throws AliceException {
        this.by = parseDate(by);
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

}
