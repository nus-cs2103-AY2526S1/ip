package scribbles.task;

import java.time.LocalDateTime;

import scribbles.parser.Parser;

/**
 * Provides the properties of an event task.
 */
public class EventTask extends Task {
    private static final String LABEL = "E";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an event task.
     *
     * @param description Description of the task.
     * @param from Event dateTime of the task starting from.
     * @param to Event dateTime of the task ending to.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event task that is either
     * complete or incomplete.
     *
     * @param description Description of the task.
     * @param from Event dateTime of the task starting from.
     * @param to Event dateTime of the task ending to.
     * @param isDone Whether the task is completed or not.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    private String getFromTo() {
        return "(from: %s to: %s)".formatted(this.from.format(Parser.PRINT_FORMAT),
                this.to.format(Parser.PRINT_FORMAT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "%s | %s | %s | %s".formatted(LABEL, super.encode(),
                this.from.format(Parser.INPUT_FORMAT), this.to.format(Parser.INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return "[%s]%s %s".formatted(LABEL, super.toString(), this.getFromTo());
    }
}
