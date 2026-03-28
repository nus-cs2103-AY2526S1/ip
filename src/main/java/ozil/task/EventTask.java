package ozil.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Events that are extensions of tasks
 */
public class EventTask extends Task {
    private String start;
    private String end;
    private Date startTime;
    private Date endTime;


    /**
     * Creates a new event.
     * @param description Description of event.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.start = startTime;
        this.end = endTime;
        try {
            this.startTime = parseDateTime(startTime);
            this.endTime = setTimeOnDate(this.startTime, endTime);
        } catch (ParseException e) {
            //Task will have no date startTime and endTime
            //Allows user to still have task but with a string instead of date
        }
    }

    @Override
    public String toString() {
        if (this.hasDate()) {
            DateFormat timeformatter = new SimpleDateFormat("HHmm");
            DateFormat formatter = new SimpleDateFormat("HHmm");
            if (formatter.format(startTime).equals("0000")) {
                formatter = new SimpleDateFormat("dd-MM-yyyy");
            } else {
                formatter = new SimpleDateFormat("dd-MM-yyyy HHmm");
            }
            return "[E]" + super.toString() + " from: " + formatter.format(this.startTime)
                   + " to: " + timeformatter.format(this.endTime);
        }
        assert !this.start.isEmpty() && !this.end.isEmpty();
        return "[E] " + super.toString() + " from: " + this.start + " to: "
                + this.end;
    }

    @Override
    public boolean hasDate() {
        return startTime != null && endTime != null;
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("E | %d | %s | %s | %s ", this.isDone ? 1 : 0, this.description,
           this.start, this.end);
    }

    @Override
    public Date getTaskDate() {
        return this.startTime;
    }
}
