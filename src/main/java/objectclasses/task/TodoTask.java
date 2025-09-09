package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.CommandFormatException;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;

/**
 * Represents a basic task with a <code>TaskType</code>, <code>Status</code>, name and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class TodoTask extends Task {

    /**
     * Constructor for creating a <code>TodoTask</code>
     *
     * @param name Name of the task.
     */
    public TodoTask(String name) {
        super(name, TaskType.TODO);
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
            throw new LynxException("");
        }

        String status = parts[1];
        String name = parts[3];
        String priority = parts[4];

        Task task = new TodoTask(name);
        try {
            task.setPriority(Integer.parseInt(priority));
        } catch (NumberFormatException e) {
            throw CommandFormatException.invalidPriority();
        }
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
     * @throws LynxException If command or name is invalid.
     */
    public static Task of(String input) throws LynxException {
        if (!input.startsWith("todo ")) {
            throw new MissingArgumentException("todo");
        }

        String[] parts = input.substring(4).split(" /p ", 2);
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        int priority = 0;
        if (parts.length > 1) {
            try {
                priority = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                throw CommandFormatException.invalidPriority();
            }
        }

        TodoTask task = new TodoTask(name);
        task.setPriority(priority);
        return task;
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
