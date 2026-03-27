package mochi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Implements a Deadline task, which is a type of Task that has a description and a due date/time.
 */
public class Deadline extends Task {
    private LocalDateTime due;

    /**
     * Creates an uncompleted Deadline task with a description and a due date/time.
     *
     * @param desc the description of the todo item
     * @throws DeadlineException if the date/time format is invalid
     */
    public Deadline(String desc, String dueDate) throws DeadlineException {
        super(desc);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.due = LocalDateTime.parse(dueDate, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                // Handle no time specified
                this.due = LocalDate.parse(dueDate).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new DeadlineException();
            }
        }
    }

    /**
     * Creates a Deadline task with a description and a due date/time, with the specified completion status.
     *
     * @param desc the description of the todo item
     * @param status the completion status of the todo item
     * @throws DeadlineException if the date/time format is invalid
     */
    public Deadline(String desc, String dueDate, boolean status) throws DeadlineException {
        this(desc, dueDate);
        if (status) {
            super.mark();
        }
    }

    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s", this.completed ? 1 : 0, super.getDescriptionSaveString(),
                this.due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.due.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm")));
    }
}
