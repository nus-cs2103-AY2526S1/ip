package tasks;

import java.time.LocalDate;

/**
 * Event Task used in the Nicholas chatbot.
 * <p>
 * Creates event tasks that requires description, start time and end time.
 */
public class EventTask extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public EventTask(String description, LocalDate startTime, LocalDate endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventTask(String description, LocalDate startTime, LocalDate endTime, boolean isDone){
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String saveFileFormat() {
        return "E|" + super.saveFileFormat() + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + startTime + "to:" + endTime + ")";
    }
}
