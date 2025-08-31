package poopiemeow.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, String deadlineStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
        }
        this.deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toFileString() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + 
               deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}