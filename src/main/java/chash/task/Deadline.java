package chash.task;

import java.time.LocalDateTime;

/** Represents a deadline task with a due date. */
public class Deadline extends Task {
    public static final String TASKTYPE = "D";
    private final String rawEnd;
    private final LocalDateTime parsedEnd;

    /**
     * Creates a new {@code Deadline} task.
     *
     * @param description Task description
     * @param endtime Raw end time string
     */
    public Deadline(String description, String endtime) {
        super(description);
        this.rawEnd = endtime;
        this.parsedEnd = TaskDateParser.tryParse(endtime);
    }

    /** {@inheritDoc} */
    @Override
    public String exportString() {
        return String.format(
                "%s | %s | %s",
                Deadline.TASKTYPE,
                super.exportString(),
                this.rawEnd
        );
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "[%s]%s (by: %s)",
                Deadline.TASKTYPE,
                super.toString(),
                TaskDateParser.format(this.parsedEnd, this.rawEnd)
        );
    }
}
