import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String TASKTYPE = "D";
    private static final DateTimeFormatter DATE_PRINTFORMAT = 
        DateTimeFormatter.ofPattern("MMM d yyyy");
    private final String endtime;
    private final LocalDateTime parsedEndTime;

    public Deadline(String description, String endtime) {
        super(description);
        this.endtime = endtime;

        LocalDateTime tmpEnd = null;
        try {
            tmpEnd = LocalDateTime.parse(endtime);
        } catch (DateTimeParseException ex) {
            //Allowed to swallow this error based on prog design
            //User can choose not to submit a ISO-8601 date string
        } finally {
            this.parsedEndTime = tmpEnd;
        }
    }

    private String getEndTime() {
        return (this.parsedEndTime != null) ? 
            this.parsedEndTime.format(Deadline.DATE_PRINTFORMAT) : this.endtime;
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s",
            Deadline.TASKTYPE,
            super.exportString(),
            this.endtime
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (by: %s)",
            Deadline.TASKTYPE,
            super.toString(),
            getEndTime()
        );
    }
}
