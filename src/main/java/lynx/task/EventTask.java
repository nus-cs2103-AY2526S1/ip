package lynx.task;

import lynx.formatter.LynxDateManager;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name, Task.TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }

    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s) (id:%d)", type.getSymbol(), status.getSymbol(),
                name, LynxDateManager.textDateTime(start), LynxDateManager.textDateTime(end), id);
    }
}