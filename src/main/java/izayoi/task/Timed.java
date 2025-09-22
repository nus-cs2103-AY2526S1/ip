package izayoi.task;

import java.util.List;

import izayoi.exception.InvalidFormatException;
import izayoi.exception.IzayoiException;
import izayoi.input.TaskDescriptor;

/**
 * Represents a Task with a fixed duration
 */
public class Timed extends Task {
    private final int time;

    /**
     * Initializes a new uncompleted task
     *
     * @param input the InputManager reading the task description
     * @throws InvalidFormatException  the input is invalid
     */
    public Timed(TaskDescriptor input) throws IzayoiException {
        super(input);
        try {
            this.time = Integer.parseInt(super.getArgument("takes"));
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Can you specify how long this is gonna take?");
        }
    }

    @Override
    public String getType() {
        return "Q";
    }

    @Override
    public String toString() {
        return String.format("%s (takes %s hours)", super.toString(), time);
    }

    @Override
    public List<String> commandify() {
        return List.of(String.format("timed %s /takes %s", getMessage(), time));
    }
}
