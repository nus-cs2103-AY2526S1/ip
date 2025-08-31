package poopiemeow.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String startTimeStr, String endTimeStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of an event cannot be empty.");
        }
        this.startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toFileString() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + 
               startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "|" + 
               endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + 
               startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: " +
               endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
