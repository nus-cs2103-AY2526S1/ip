package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import amogus.AmogusException;

/**
 * Represents a Deadlines object.
 */
public class Deadlines extends Task {
    private final LocalDateTime byDate;
    /**
     * Creates the Tasks.Deadlines object.
     *
     * @param description description of Tasks.Deadlines task
     * @param by deadline
     * @throws AmogusException insufficient information to create deadlines
     */
    public Deadlines(String description, String by) throws AmogusException {
        super(description);
        if (Objects.equals(description, "") || Objects.equals(by, "")) {
            throw new AmogusException("Oh no! Please provide full information regarding your deadline!");
        }
        by = by.trim();
        try {
            if (by.contains(" ")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
                this.byDate = LocalDateTime.parse(by, dtf);
            } else {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate dateOnly = LocalDate.parse(by, df);
                this.byDate = dateOnly.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new AmogusException("Invalid date format. Use d/M/yyyy or d/M/yyyy HH:mm, e.g., 2/3/2003 or 2/3/2003 14:00");
        }
    }

    /**
     * Returns the type of task.
     * @return deadline type of task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Correct display of task for easier understanding.
     * @return display string of task.
     */
    @Override
    public String getDisplayString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return super.getDisplayString() + " (by: " + byDate.format(outputFormatter) + ")";
    }


    /**
     * @return String representation of Deadline task.
     */
    @Override
    public String toString() {
        String type = "D | ";
        String done = isDone() ? "1" : "0";
        return type + done + " | " + getDescription() + " | " + byDate.format(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")) + getTag();
    }
}
