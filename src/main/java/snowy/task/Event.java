package snowy.task;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific start time and an end time.
 */
public class Event extends Task{
    
    // private String startTime;
    // private String endTime;
    private String time;
    // private LocalDateTime dateTime;

    public Event(String task, String time) {
        super(task);
        this.time = time;
        // this.dateTime = LocalDateTime.parse(time);
    }

    public Event(String task, boolean isDone, String time) {
        super(task, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" +
                (this.checkStatus() ? "[X] " : "[ ] ") +
                super.toString() +
                // " (from: " +
                // this.startTime +
                // " to: " +
                // this.endTime +
                // ")";
                " (" +
                this.time +
                // this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + 
                ")";
    }
}
