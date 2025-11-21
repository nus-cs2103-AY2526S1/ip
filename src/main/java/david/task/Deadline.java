package david.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * A task that has deadline.
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private String by;
    private boolean hasTime;
    private boolean isValidDate;
    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * @param description Description of the deadline task.
     * @param by The end time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            if (this.by.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.hasTime = true;
                this.isValidDate = true;
                this.dateTime = LocalDateTime.parse(this.by, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                hasTime = false;
                isValidDate = true;
                this.date = LocalDate.parse(this.by, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            hasTime = false;
            isValidDate = false;
        }
    }

    private String printDate() {
        if (!isValidDate) {
            return this.by;
        }
        if (hasTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return dateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return date.format(outputFormatter);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TYPE,
                super.toString(), printDate());
    }

    @Override
    public String serialize() {
        return String.format("%s | %s | %s", TYPE,
                super.serialize(), printDate());
    }

    @Override
    public Task copy() {
        Task t = new Deadline(this.getDescription(), this.by);
        if (this.getIsDone()) {
            t.markAsDone();
        }
        return t;
    }
}
