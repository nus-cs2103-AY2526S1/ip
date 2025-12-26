package gene.tasks;

import java.time.LocalDateTime;

import gene.enums.Commands;

public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        assert description != null;
        assert by != null;
        this.by = Task.dateTimeParser(by);
    }

    @Override
    public String toDbString() {
        return String.format("%s| %d | %s | %s", Commands.DEADLINE, isDone ? 1 : 0, description, this.dateTimeToDbString(by));
    }

    /**
     * Checks if a reminder is needed for the task based on the given date time.
     *
     * @param dt the date time to be checked against
     * @return boolean indicating if a reminder is needed
     */
    @Override
    public boolean isReminderNeeded(LocalDateTime dt) {
        return (this.by.isEqual(dt) || this.by.isBefore(dt));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, this.dateTimeToString(by));
    }
}
