package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import amogus.AmogusException;

/**
 * Represents an Event object.
 */
public class Event extends Task {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Creates the Tasks.Event object.
     *
     * @param description description of Tasks.Event task
     * @param start start date
     * @param end end date
     * @throws AmogusException insufficient information to create Tasks.Event task
     */
    public Event(String description, String start, String end) throws AmogusException {
        super(description);
        if (Objects.equals(description, "") || Objects.equals(start, "") || Objects.equals(end, "")) {
            throw new AmogusException("Oh no! Please provide full information regarding your event!");
        }
        this.startDate = parseDateTime(start);
        this.endDate = parseDateTime(end);
    }

    private LocalDateTime parseDateTime(String input) throws AmogusException {
        input = input.trim();
        try {
            if (input.contains(" ")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                return LocalDateTime.parse(input, dtf);
            } else {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate dateOnly = LocalDate.parse(input, df);
                return dateOnly.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new AmogusException(
                    "Invalid date format. Use d/M/yyyy or d/M/yyyy HHmm, e.g., 2/3/2003 or 2/3/2003 1400");
        }
    }

    /**
     * Returns the type of task.
     * @return event type of task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Correct display of task for easier understanding.
     * @return display string of task.
     */
    @Override
    public String getDisplayString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "[" + getType() + "][" + (isDone() ? "X" : " ") + "] "
                + getDescription() + " (from: " + startDate.format(outputFormatter)
                + " to: " + endDate.format(outputFormatter) + ")" + getTag();
    }

    /**
     * @return string representation of Event task
     */
    @Override
    public String toString() {
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String type = "E | ";
        String done = isDone() ? "1" : "0";
        return type + done + " | " + getDescription() + " | "
                + startDate.format(storageFormatter) + " | "
                + endDate.format(storageFormatter) + getTag();
    }

}
