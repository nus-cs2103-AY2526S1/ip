package gene.tasks;

import java.time.LocalDateTime;

import gene.enums.Commands;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, String from, String to, boolean b) {
        super(description, b);
        assert description != null;
        assert from != null;
        assert to != null;
        this.from = dateTimeParser(from);
        this.to = dateTimeParser(to);
    }

    @Override
    public String toDbString() {
        return String.format("%s | %d | %s | %s | %s", Commands.EVENT, isDone ? 1 : 0, description, this.dateTimeToDbString(from), this.dateTimeToDbString(to));
    }

    @Override
    public boolean isReminderNeeded(LocalDateTime dt) {
        return this.to.isBefore(dt) || this.to.isEqual(dt);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(), description, this.dateTimeToString(from), this.dateTimeToString(to));
    }
}
