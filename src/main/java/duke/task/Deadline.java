package duke.task;

import duke.exception.IncorrectFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a deadline task
 * Has additional field LocalDateTime deadline
 */
public class Deadline extends Task {
    private String by;
    private LocalDateTime deadline;

    /**
     * Constructor for a {@code Deadline}
     * @param name Name of the task
     * @param by Date and time by which task is to be completed.
     */
    public Deadline(String name, String by) {
        super(name);
        this.convertToDate(by);
    }

    public String getBy() {
        return this.by;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Formats the deadline given into a LocalDateTime object.
     * Binds field to newly created object.
     * @param by Deadline by which task is to be completed.
     */
    public void convertToDate(String by) {
        try {
            this.by = by.trim();
            String[] elems = this.by.split(" ");

            // --- Parse date ---
            String[] dates = elems[0].split("/");   // expected: yyyy/MM/dd
            if (dates.length != 3) {
                throw new IncorrectFormatException();
            }
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);

            int hour = 0;
            int minute = 0;

            // --- Parse time (if provided) ---
            if (elems.length == 2) {
                String[] times = elems[1].split(":");  // expected: HH:mm
                if (times.length != 2) {
                    throw new IncorrectFormatException();
                }
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
            }

            // --- Create LocalDateTime ---
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
            this.deadline = dateTime;

            // --- Format nicely for display ---
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            this.by = dateTime.format(formatter);

        } catch (IncorrectFormatException | DateTimeException | NumberFormatException e) {
            // If parsing fails, keep original string
            this.deadline = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
