package izayoi.task;

import java.time.LocalDate;
import java.util.List;

import izayoi.input.InputReader;
import izayoi.exception.IzayoiException;

/**
 * Represents a Task with a deadline
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Initializes a new uncompleted Deadline task
     * @param input the InputManager reading the task description
     * @throws IzayoiException if the input is invalid
     */
    public Deadline(InputReader input) throws IzayoiException {
        super(input);
        this.deadline = LocalDate.parse(super.getArgument("by"));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline.format(DATETIME_FORMAT));
    }

    @Override
    public List<String> commandify() {
        return List.of(String.format("deadline %s /by %s", getMessage(), deadline));
    }
}
