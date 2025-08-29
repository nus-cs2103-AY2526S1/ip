package phuc.model;

import phuc.parser.DateTimeProcess;
import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String writeToFile() {
        return "E | " + isDone + " | " + description + " | " + DateTimeProcess.formatForFile(startDate) + " | " + DateTimeProcess.formatForFile(endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeProcess.formatForDisplay(startDate) + " to: " + DateTimeProcess.formatForDisplay(endDate) + ")";
    }
}
