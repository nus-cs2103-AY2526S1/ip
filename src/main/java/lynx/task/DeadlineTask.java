package lynx.task;

import lynx.formatter.LynxDateManager;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name, Task.TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() { return deadline; }

    public String testRepresentation() {
        return String.format("%s%s %s (by: %s)", type.getSymbol(), status.getSymbol(),
                name, LynxDateManager.textDateTime(deadline));
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s) (id:%d)", type.getSymbol(), status.getSymbol(),
                name, LynxDateManager.textDateTime(deadline), id);
    }
}