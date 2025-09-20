package airy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a subclass from task
 * A task with a deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate dueDate;

    /**
     * Constructs a new Deadline task with the specified name and due date.
     *
     * @param taskName the description or name of the deadline task
     * @param dueDate the due date of the task in {yyyy-MM-dd} format
     * @throws AiryException if the date string cannot be parsed using the expected format
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        assert taskName != null && !taskName.isBlank() : "Task name must not be empty";
        try {
            this.dueDate = LocalDate.parse(dueDate, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AiryException("Invalid date format, please use yyyy-MM-dd, e.g. 2025-06-02");
        }
    }

    /**
     * Returns a string representation of the Deadline task for display to the user.
     *
     * @return a formatted string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                dueDate.format(OUTPUT_FORMAT));
    }

    /**
     * Returns the dueDate in input format for Storage
     *
     * @return a string containing the due date in storage format
     */
    @Override
    public String getExtraDetailsForStorage() {
        return dueDate.format(INPUT_FORMAT);
    }
}
