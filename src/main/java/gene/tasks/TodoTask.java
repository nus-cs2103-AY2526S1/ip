package gene.tasks;

import gene.enums.Commands;

import java.time.LocalDateTime;

public class TodoTask extends Task {
    public TodoTask(String s, boolean b) {
        super(s, b);
        assert s != null;
    }

    @Override
    public boolean isReminderNeeded(LocalDateTime dt) {
        return false;
    }

    @Override
    public String toDbString() {
        return String.format("%s | %d | %s", Commands.TODO, isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
