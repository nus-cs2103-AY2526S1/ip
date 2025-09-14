package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;

/**
 * Represents a basic task with a <code>TaskType</code>, <code>Status</code>, name, priority and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class TodoTask extends Task {

    /**
     * Constructor for creating a <code>TodoTask</code>
     *
     * @param name Name of the task.
     * @param priority Priority of the task.
     */
    public TodoTask(String name, int priority) {
        super(name, priority, TaskType.TODO);
    }

    /**
     * Creates a <code>TodoTask</code> and returns it.
     *
     * @param parts Parsed representation of a <code>TodoTask</code>.
     * @return <code>TodoTask</code> created.
     * @throws LynxException If input is of invalid format.
     */
    public static Task of(String[] parts) throws LynxException {
        if (parts.length != 5) {
            throw new MissingArgumentException("todo");
        }

        String status = parts[1];
        String name = parts[3];
        int priority = parsePriority(parts[4]);

        Task task = new TodoTask(name, priority);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        return task;
    }

    /**
     * Creates a <code>TodoTask</code> and returns it.
     *
     * @param input User command in the form "todo [name]".
     * @return <code>TodoTask</code> created.
     * @throws LynxException If command, name or priority is invalid.
     */
    public static Task of(String input) throws LynxException {
        if (!input.startsWith("todo ")) {
            throw new MissingArgumentException("todo");
        }

        String[] parts = input.substring(4).split(" /p ", 2);
        String name = parts[0].trim();
        checkName(name);

        int priority = 0;
        if (parts.length > 1) {
            priority = parsePriority(parts[1].trim());
        }

        return new TodoTask(name, priority);
    }

    /**
     * Checks if the task is active on the given date.
     *
     * @return False.
     */
    public boolean isActive(LocalDateTime dateTime) {
        return false;
    }

}
