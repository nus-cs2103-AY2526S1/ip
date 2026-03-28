package cattis.task;

import java.util.Arrays;
import java.util.List;

import cattis.exception.CattisException;
import cattis.exception.CattisLoadFileException;

/**
 * Represents an abstract task with a name and completion status.
 * This class serves as a base for specific task types such as <code>TodoTask</code>,
 * <code>DeadlineTask</code>, and <code>EventTask</code>.
 * It provides common functionality for marking tasks as completed / unfinished, and encoding/decoding tasks to
 * save as in a file.
 *
 * <p>Tasks can be serialized and deserialized using a custom format with the {@code SPLITTER} delimiter.
 * The {@code decode} method reconstructs a {@code Task} object from a string payload.
 */
public abstract class Task {
    public static final String SPLITTER = "<>";
    public static final String DATE_TIME_INPUT_FORMATTER = "yyyy-MM-dd";
    public static final String DATE_TIME_OUTPUT_FORMATTER = "MMM dd yyyy";
    private static final String CHECKED_TASK_ICON = "[X]";
    private static final String UNCHECKED_TASK_ICON = "[ ]";

    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Decodes a serialized task string into a <code>Task</code> object.
     * The payload must follow the format:
     * <code>TYPE | STATUS | TASK_NAME | [DEADLINE / START DATE] | [END DATE]</code>.
     *
     * @param payload the encoded task string
     * @return the decoded <code>Task</code> object
     * @throws CattisException if the payload cannot be parsed
     */
    public static Task decode(String payload) throws CattisException {
        List<String> arr = Arrays.stream(payload.split(Task.SPLITTER))
                .map(String::trim).toList();
        if (arr.size() < 3) {
            throw new CattisLoadFileException("Failed to load task from disk");
        }
        String taskType = arr.get(0);
        boolean isChecked;
        isChecked = CHECKED_TASK_ICON.equals(arr.get(1));
        String taskName = arr.get(2);

        Task task;
        switch (taskType) {
        case TodoTask.ICON:
            task = new TodoTask(taskName, isChecked);
            break;
        case DeadlineTask.ICON:
            if (arr.size() != 4) {
                throw new CattisLoadFileException("Failed to load task from disk");
            }
            task = new DeadlineTask(taskName, arr.get(3), isChecked);
            break;
        case EventTask.ICON:
            if (arr.size() != 5) {
                throw new CattisLoadFileException("Failed to load task from disk");
            }
            task = new EventTask(taskName, arr.get(3), arr.get(4), isChecked);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return this.isCompleted ? CHECKED_TASK_ICON : UNCHECKED_TASK_ICON;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Encodes the task into a string representation for storage.
     *
     * @return the encoded string of the task
     */
    public abstract String toEncodedString();

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskName;
    }
}

