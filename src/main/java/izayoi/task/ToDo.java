package izayoi.task;

import izayoi.input.InputReader;
import izayoi.exception.IzayoiException;

import java.util.List;

/**
 * Represents a Task with no deadline or duration
 */
public class ToDo extends Task {

    /**
     * Initializes a new uncompleted ToDo task
     * @param input the InputManager reading the task description
     * @throws IzayoiException if the input is invalid
     */
    public ToDo(InputReader input) throws IzayoiException {
        super(input);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public List<String> commandify() {
        return List.of(String.format("todo %s", getMessage()));
    }
}
