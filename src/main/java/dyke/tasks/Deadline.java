package dyke.tasks;

import dyke.parse.DateTimeParser;
import dyke.parse.DykeException;

/**
 * Houses methods for {@code Deadline} task
 */
public class Deadline extends Task {
    private DateTimeParser deadline;

    /**
     * Creates a {@code Deadline} task.
     * @param desc Description of task.
     * @param deadline End datetime
     * @throws DykeException If inputs are unrecognized.
     */
    public Deadline(String desc, String deadline) throws DykeException {
        super(desc);
        this.deadline = DateTimeParser.parse(deadline);
    }

    public DateTimeParser getDeadline() {
        return deadline;
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | "
                + this.deadline.reString();
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline.reString() + ")";
    }
}
