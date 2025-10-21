package Task;

import java.time.LocalDate;
import java.util.Optional;

import static Parser.DateParser.formatForDisplay;
import static Parser.DateParser.formatForStorage;

/**
 * Deadline task with description and deadline.
 */
public class Deadline extends TaskItem {
    private final LocalDate deadline;

    /**
     * Initialises deadline task.
     *
     * @param name Description of task.
     * @param isDone Completion status of task.
     * @param by Deadline of task.
     */
    public Deadline(String name, boolean isDone, LocalDate by) {
        super(name);
        assert by != null : "Deadline: /by must not be empty";
        this.deadline = by;
        if (isDone) {
            super.markDone();
        }
    }

    /**
     * Returns tag to be printed in list.
     */
    @Override
    public String typeTag() {
        return "[D]";
    }

    /**
     * Returns additional formatting to include deadline in display.
     */
    @Override
    public String extraDetail() {
        return " (by: " + formatForDisplay(deadline) + ")";
    }

    /**
     * Returns string to save task in storage.
     */
    @Override
    public String toSaveString() {
        assert name.indexOf('|') < 0 : "Save: name must not contain '|'";
        return "D|" + isDone + "|" + name + "|" + formatForStorage(deadline);
    }

    /**
     * Returns deadline of task.
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.of(deadline);
    }
}
