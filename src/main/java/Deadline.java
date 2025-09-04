import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String TASKTYPE = "D";
    private final String rawEnd;
    private final LocalDateTime parsedEnd;

    public Deadline(String description, String endtime) {
        super(description);
        this.rawEnd = endtime;
        this.parsedEnd = TaskDateParser.tryParse(endtime);
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s",
            Deadline.TASKTYPE,
            super.exportString(),
            this.rawEnd
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (by: %s)",
            Deadline.TASKTYPE,
            super.toString(),
            TaskDateParser.format(this.parsedEnd, this.rawEnd)
        );
    }
}
