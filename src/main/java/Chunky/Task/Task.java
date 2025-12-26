package Chunky.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task class to create Task Object
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Convert YYYY-MM-DD to MMM d yyyy format
     *
     * @param dateInput Date to be formatted
     * @return formatted date i.e Oct 25 2025
     */
    public static String formatDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return dateInput; // Return as-is, no validation here
        }
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[ %s ] %s", this.getStatusIcon(), this.description);
    }
}
