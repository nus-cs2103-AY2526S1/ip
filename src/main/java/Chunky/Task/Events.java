package Chunky.Task;

/**
 * Events class, has a start and end date/time as a task object
 */
public class Events extends Task {
    private String startTime;
    private String endTime;
    private String location;

    public Events(String description, String startTime, String endTime, String location) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                String.format(" (from: %s to: %s) at %s", super.formatDate(this.startTime),
                        super.formatDate(this.endTime), this.location);
    }

    public String getFrom() {
        return this.startTime;
    }

    public String getTo() {
        return this.endTime;
    }

    public String getLocation() {
        return this.location;
    }
}
