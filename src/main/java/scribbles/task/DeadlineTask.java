package scribbles.task;

import java.time.LocalDateTime;

import scribbles.parser.Parser;

/**
 * Provides the properties of a deadline task.
 */
public class DeadlineTask extends Task {
    private static final String LABEL = "D";
    private final LocalDateTime by;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline of the task to complete by.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task that is either
     * complete or incomplete.
     *
     * @param description Description of the task.
     * @param by Deadline of the task to complete by.
     * @param isDone Whether the task is completed or not.
     */
    public DeadlineTask(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    private String getBy() {
        return "(by: %s)".formatted(this.by.format(Parser.PRINT_FORMAT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "%s | %s | %s".formatted(LABEL, super.encode(),
                this.by.format(Parser.INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return "[%s]%s %s".formatted(LABEL, super.toString(), this.getBy());
    }
}
