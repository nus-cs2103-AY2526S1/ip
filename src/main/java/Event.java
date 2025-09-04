import java.time.LocalDateTime;

public class Event extends Task {
    public static final String TASKTYPE = "E";
    private final String rawStart;
    private final String rawEnd;
    private final LocalDateTime parsedStart;
    private final LocalDateTime parsedEnd;

    public Event(String description, String starttime, String endtime) {
        super(description);
        this.rawStart = starttime;
        this.rawEnd = endtime;
        this.parsedStart = TaskDateParser.tryParse(starttime);
        this.parsedEnd = TaskDateParser.tryParse(endtime);
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s | %s",
            Event.TASKTYPE,
            super.exportString(),
            this.rawStart,
            this.rawEnd
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (from: %s to: %s)",
            Event.TASKTYPE,
            super.toString(),
            TaskDateParser.format(this.parsedStart, this.rawStart),
            TaskDateParser.format(this.parsedEnd, this.rawEnd)
        );
    }
}
