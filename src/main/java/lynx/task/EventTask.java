package lynx.task;

import lynx.formatter.LynxDateManager;

import java.time.LocalDateTime;

public class EventTask extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String testRepresentation() {
        return String.format("%s (from: %s to: %s)", super.testRepresentation(),
                LynxDateManager.textDateTime(start), LynxDateManager.textDateTime(end));
    }

}