import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String TASKTYPE = "E";
    private static final DateTimeFormatter DATE_PRINTFORMAT = 
        DateTimeFormatter.ofPattern("MMM d yyyy");
    private final String starttime;
    private final String endtime;
    private final LocalDateTime parsedStartTime;
    private final LocalDateTime parsedEndTime;

    public Event(String description, String starttime, String endtime) {
        super(description);
        this.starttime = starttime;
        this.endtime = endtime;

        LocalDateTime tmpStart = null;
        try {
            tmpStart = LocalDateTime.parse(starttime);
        } catch (DateTimeParseException ex) {
            //Allowed to swallow this error based on prog design
            //User can choose not to submit a ISO-8601 date string
        } finally {
            this.parsedStartTime = tmpStart;
        }

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

    //Note: consider using interface as both Event and Deadline fulfills this contract
    private String getStartTime() {
        return (this.parsedStartTime != null) ? 
            this.parsedStartTime.format(Event.DATE_PRINTFORMAT) : this.starttime;
    }

    private String getEndTime() {
        return (this.parsedEndTime != null) ? 
            this.parsedEndTime.format(Event.DATE_PRINTFORMAT) : this.endtime;
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s | %s",
            Event.TASKTYPE,
            super.exportString(),
            this.starttime,
            this.endtime
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (from: %s to: %s)",
            Event.TASKTYPE,
            super.toString(),
            getStartTime(),
            getEndTime()
        );
    }
}
