package Task;

import java.time.LocalDate;
import java.util.Optional;

import static Parser.DateParser.formatForDisplay;
import static Parser.DateParser.formatForStorage;

/**
 * Event task with description, start and end dates.
 */
public class Event extends TaskItem {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initialises event task.
     *
     * @param name Description of task.
     * @param isDone Completion status of task.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name);
        assert from != null : "Event: /from missing";
        assert to != null : "Event: /to missing";
        this.from = from;
        this.to = to;
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns tag to be printed in list.
     */
    @Override
    protected String typeTag() {
        return "[E]";
    }

    /**
     * Returns additional formatting to include start and end dates in display.
     */
    @Override
    protected String extraDetail() {
        return " (from: " + formatForDisplay(from) + " to: " + formatForDisplay(to) + ")";
    }

    /**
     * Returns string to save task in storage.
     */
    @Override public String toSaveString() {
        assert name.indexOf('|') < 0 : "Save: name must not contain '|'";
        return "E|" + isDone + "|" + name + "|" + formatForStorage(from) + "|" + formatForStorage(to);
    }

    /**
     * Returns start date of task.
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.of(from);
    }
}
