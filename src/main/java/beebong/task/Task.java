package beebong.task;

import beebong.exception.InvalidSerializedTaskDataException;
import beebong.util.StringUtil;

/**
 * Represents a generic task with a name and completion status.
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;
    protected static String SAVE_DELIMITER = " ";

    /**
     * Creates a task with the given name.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    // SerialiseTask declared abstract for ensure child classes implement it,
    // but deserializeTask cannot be declared as an abstract method here
    // due to its nature of being static
    /**
     * Returns a serialized string representation of the task for saving.
     *
     * @return Serialized task string.
     */
    public String serializeTask() {
        return ((isCompleted()) ? "1" : "0") + SAVE_DELIMITER + StringUtil.encode(this.getName());
    };

    /**
     * Returns a {@link Task} deserialized from its string representation.
     * <p>
     * The method determines the task type from the first character
     * of the string and delegates parsing to the appropriate subclass.
     * </p>
     *
     * @param taskStr Serialized task string.
     * @return Deserialized task instance.
     * @throws InvalidSerializedTaskDataException If taskStr is invalid or the task type is unrecognized.
     */
    public static Task deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // Each instance of deserializeTask in the child classes
        // also throw InvalidSerializedTaskDataException
        // If any of them throw it here, just let the callee
        // of this method handle it
        if (taskStr.startsWith("T")) {
            return ToDoTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("D")) {
            return DeadlineTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("E")) {
            return EventTask.deserializeTask(taskStr);
        }
        throw new InvalidSerializedTaskDataException();
    }

    @Override
    public String toString() {
        return "[" + ((this.isCompleted) ? "X" : " " ) + "] " + this.name;
    }
}
