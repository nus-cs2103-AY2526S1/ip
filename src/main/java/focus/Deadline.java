package focus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be completed by a specific date.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /** User input and storage format. */
    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Deadline event with the given description and due date.
     * The date is expected in format code yyyy-MM-dd HHmm.
     *
     * @param description Description of this deadline.
     * @param deadline Due date in format yyyy-MM-dd HHmm.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a");
        if (isTagged()) {
            return String.format("       [D]%s (by: %s) %s",
                    super.toString(), deadline.format(outputDateFormat), getTag().toString());
        }
        return String.format("       [D]%s (by: %s)", super.toString(), deadline.format(outputDateFormat));
    }

    /**
     * Returns a storage-friendly representation of this deadline.
     *
     * @return Encoded string to be written to disk.
     */
    @Override
    public String toStorageString() {
        if (isTagged()) {
            return String.format("D %s | %s | %s",
                    super.toStorageString(), this.deadline.format(inputFormat), getTag().toString());
        }
        return String.format("D %s | %s", super.toStorageString(), this.deadline.format(inputFormat));
    }

}
