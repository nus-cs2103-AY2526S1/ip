package alice.task;

import alice.Task;
import alice.exceptions.AliceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateTask extends Task {
    protected static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    public DateTask(String description) {
        super(description);
    }

    public DateTask(String description, boolean isDone) {
        super(description, isDone);
    }

    protected LocalDateTime parseDate(String input) throws AliceException {
        try {
            return LocalDateTime.parse(input.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AliceException("Invalid date! Use d/M/yyyy HHmm (e.g. 2/12/2019 1800).");
        }
    }
}
