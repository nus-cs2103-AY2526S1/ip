package beebong.task;

import beebong.exception.InvalidSerializedTaskDataException;
import beebong.util.StringUtil;

/**
 * Represents a simple to-do task with a name and completion status.
 */
public class ToDoTask extends Task {
    /**
     * Creates a new incomplete to-do task.
     *
     * @param name the name of the task.
     */
    public ToDoTask(String name) {
        super(name);
    }

    private ToDoTask(String name, boolean isCompleted) {
        super(name);
        if (isCompleted) {
            markCompleted();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serializeTask() {
        return "T" + Task.SAVE_DELIMITER + super.serializeTask();
    }

    /**
     * Deserializes a string back into a {@link ToDoTask}.
     *
     * @param taskStr the serialized task string.
     * @return the corresponding {@link ToDoTask} object.
     * @throws InvalidSerializedTaskDataException If taskStr is invalid.
     */
    public static ToDoTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVE_DELIMITER, -1);
        if (taskData.length != 3) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["T", "0", "NAME"]
        String name = StringUtil.decode(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");

        return new ToDoTask(name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
