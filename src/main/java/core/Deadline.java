package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import util.TronaldDumpException;

/**
 * Represents a deadline task with a description and a deadline date/time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * @param description Description of the deadline task, including the deadline date/time after "/by".
     * @param isDone Boolean indicating if the task is done.
     * @throws TronaldDumpException if the description does not contain a valid date/time after "/by".
     */
    public Deadline(String description, boolean isDone) {
        super(description.split(" /by ")[0], isDone);
        String[] parts = description.split(" /by ");
        String errorMessageForInvalidDeadlineDescription = "Deadline description must include a date/time after '/by'.";
        if (parts.length < 2) {
            throw new TronaldDumpException(errorMessageForInvalidDeadlineDescription);
        }
        String dateStr = parts[1].trim();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.deadline = LocalDateTime.parse(dateStr, dateTimeFormat);
        } catch (DateTimeParseException e1) {
            try {
                this.deadline = LocalDate.parse(dateStr, dateFormat).atStartOfDay();
            
            } catch (DateTimeParseException e2) {
                String errorMessageForInvalidDateFormat = "Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm.";
                throw new TronaldDumpException(errorMessageForInvalidDateFormat);
            }
        }
    }


    /**
     * @return LocalDateTime representing the deadline of the task.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * @return String representation of the task for storage in the file.
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineDescriptionForStorage = "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /by ";
        // If time is midnight, store as date only
        if (deadline.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return deadlineDescriptionForStorage
                    + deadline.toLocalDate().format(dateFormat);
        } else {
            return deadlineDescriptionForStorage
                    + deadline.format(dateTimeFormat);
        }
    }

    /**
     * Compares this deadline with another task for ordering.
     * Deadlines are sorted chronologically by their deadline date/time.
     * @param other The task to compare with
     * @return A negative integer, zero, or a positive integer as this deadline is less than,
     *         equal to, or greater than the other
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.deadline.compareTo(otherDeadline.deadline);
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.deadline.compareTo(otherEvent.getStartDateTime());
        } else {
            // For Todo tasks, deadlines come first (earlier in sort order)
            return -1;
        }
    }

    /**
     * @return String representation of the task for display to the user.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        DateTimeFormatter outputFormatDateOnly = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (deadline.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return String.format("[D] %s (by: %s)", super.toString(),
                    deadline.toLocalDate().format(outputFormatDateOnly));
        } else {
            return String.format("[D] %s (by: %s)", super.toString(),
                    deadline.format(outputFormatWithTime));
        }
    }
}
