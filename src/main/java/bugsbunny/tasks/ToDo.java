package bugsbunny.tasks;

import java.time.LocalDateTime;

/**
 * A simple to-do task that has no date/time.
 */
public class ToDo extends Task {
    /**
     * Returns an instance of ToDo class.
     *
     * @param description Task name.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns an instance of ToDo class.
     * Used only by {@link #convertFromStorageFormat(String)}.
     *
     * @param description Task name.
     * @param isDone Completion status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("T | %s", super.convertToStorageFormat());
    }

    @Override
    public boolean isDueBy(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}
