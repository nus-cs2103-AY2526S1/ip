package uxie.tasks;

import java.time.LocalDateTime;
import java.util.List;

import uxie.interfaces.DateTimeParse;

/**
 * Deadlines are Tasks that need to be done by a specific date/time.
 *
 * @author junyan-k
 */
public class Deadline extends Task {

    /** Deadline of task. Stored as {@link LocalDateTime}. */
    private LocalDateTime deadline;

    /**
     * Generates a Deadline with provided description and deadline.
     * Deadline is incomplete by default.
     * @see Task#Task(String)
     */
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Generates a Deadline with provided description and deadline, with initial completion status.
     * @see Task#Task(boolean, String)
     */
    public Deadline(boolean isCompleted, String desc, LocalDateTime deadline) {
        super(isCompleted, desc);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     *
     * @return "D"
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * {@inheritDoc}
     *
     * @return List containing ({@link #deadline})
     */
    @Override
    public List<LocalDateTime> getTimeArguments() {
        return List.of(deadline);
    }

    /**
     * Returns Deadline as String.
     * Format: "[D][<'X' if completed, ' ' if not>] {desc} (by: {@link #deadline})"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                getSymbol(), super.toString(), DateTimeParse.parseOutput(deadline));
    }

    /**
     * Returns true if both Deadlines are equal.
     * Two Todos are equal if they have the same description and by LDTs.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline d) {
            return d.getDesc().equals(this.getDesc()) &&
                    d.deadline.equals(this.deadline);
        }
        return false;
    }

}
