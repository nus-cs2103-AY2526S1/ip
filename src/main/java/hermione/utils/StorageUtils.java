package hermione.utils;

import java.time.LocalDateTime;

import hermione.tasks.Deadline;
import hermione.tasks.Event;
import hermione.tasks.FixedDurationTask;
import hermione.tasks.Task;
import hermione.tasks.TaskType;
import hermione.tasks.ToDo;

/**
 * Represents a serializer for tasks in the Hermione application.
 * Handles the serialization and deserialization of tasks, ensuring that
 * the task type, completion status, and description are correctly processed.
 */
public class StorageUtils {

    private static final String COMPLETED_TRUE = "1";
    private static final String COMPLETED_FALSE = "0";

    /**
     * Deserializes a line from the CSV file into a Task object.
     * This method splits the line by commas, extracts the task type, completion
     * status, and description, and creates the appropriate Task object based on the
     * type.
     *
     * @param line The line from the CSV file representing a task.
     * @return Task object created from the line.
     */
    public Task deserialize(String line) {
        String[] fields = line.split(",");
        validateFieldCount(fields, 3); // minimum required fields

        String taskType = getFieldSafely(fields, 0);
        boolean isCompleted = parseBinaryToBoolean(getFieldSafely(fields, 1));
        String description = getFieldSafely(fields, 2);

        return buildTask(taskType, isCompleted, description, fields);
    }

    /**
     * Serializes a Task object into a string format suitable for CSV storage.
     * This method constructs a string that includes the task type, completion
     * status, description, and any type-specific fields (like date for Deadline or
     * Event).
     *
     * @param task The Task object to be serialized.
     * @return A string representation of the Task object formatted for CSV storage.
     */
    public String serialize(Task task) {
        String baseFields = buildBaseFields(task);
        String typeSpecificFields = buildTypeSpecificFields(task);
        return baseFields + typeSpecificFields;
    }

    /* Field extraction and validation methods */
    private void validateFieldCount(String[] fields, int minimumCount) {
        if (fields.length < minimumCount) {
            throw new IllegalArgumentException(
                    "Insufficient fields for task. Expected at least " + minimumCount + " fields.");
        }
    }

    private String getFieldSafely(String[] fields, int index) {
        if (fields.length <= index) {
            throw new IllegalArgumentException("Missing expected field at index: " + index);
        }
        return fields[index].trim();
    }

    private LocalDateTime parseDateField(String[] fields, int index) {
        String dateString = getFieldSafely(fields, index);
        return dateString.isEmpty() ? null : DateUtils.undoFormatDate(dateString);
    }

    /* Task building methods using Builder Pattern */
    private Task buildTask(String taskType, boolean isCompleted, String description, String[] fields) {
        TaskType type = TaskType.fromCode(taskType);
        return switch (type) {
            case TODO -> buildToDo(description, isCompleted);
            case DEADLINE -> buildDeadline(description, isCompleted, fields);
            case EVENT -> buildEvent(description, isCompleted, fields);
            case FIXED_DURATION_TASK -> buildFixedDurationTask(description, isCompleted, fields);
        };
    }

    private ToDo buildToDo(String description, boolean isCompleted) {
        return new ToDo(description, isCompleted);
    }

    private FixedDurationTask buildFixedDurationTask(String description, boolean isCompleted, String[] fields) {
        int duration = parseStringToInt(getFieldSafely(fields, 3));
        return new FixedDurationTask(description, isCompleted, duration);
    }

    private Deadline buildDeadline(String description, boolean isCompleted, String[] fields) {
        LocalDateTime parsedBy = parseDateField(fields, 3);
        return new Deadline(description, isCompleted, parsedBy);
    }

    private Event buildEvent(String description, boolean isCompleted, String[] fields) {
        LocalDateTime parsedFrom = parseDateField(fields, 3);
        LocalDateTime parsedTo = parseDateField(fields, 4);
        return new Event(description, isCompleted, parsedFrom, parsedTo);
    }

    /* Serialization methods */
    private String buildBaseFields(Task task) {
        String taskType = getTaskType(task);
        String completed = parseBooleanToBinary(task.isCompleted());
        String description = task.getDescription();
        return String.join(", ", taskType, completed, description);
    }

    private String buildTypeSpecificFields(Task task) {
        return switch (getTaskTypeEnum(task)) {
            case TODO -> "";
            case DEADLINE -> buildDeadlineFields((Deadline) task);
            case EVENT -> buildEventFields((Event) task);
            case FIXED_DURATION_TASK -> buildFixedDurationTaskFields((FixedDurationTask) task);
        };
    }

    private String buildDeadlineFields(Deadline deadline) {
        return ", " + DateUtils.formatDate(deadline.getBy());
    }

    private String buildEventFields(Event event) {
        return ", " + DateUtils.formatDate(event.getFrom()) + ", " + DateUtils.formatDate(event.getTo());
    }

    private String buildFixedDurationTaskFields(FixedDurationTask task) {
        return ", " + task.getDuration();
    }

    /* Utility methods */
    private boolean parseBinaryToBoolean(String binary) {
        return switch (binary) {
            case COMPLETED_FALSE -> false;
            case COMPLETED_TRUE -> true;
            default -> throw new IllegalArgumentException("Invalid binary value: " + binary);
        };
    }

    private String parseBooleanToBinary(boolean bool) {
        return bool ? COMPLETED_TRUE : COMPLETED_FALSE;
    }

    private int parseStringToInt(String str) {
        return NumberUtils.parsePositiveInt(str);
    }

    private String getTaskType(Task task) {
        return getTaskTypeEnum(task).getCode();
    }

    private TaskType getTaskTypeEnum(Task task) {
        if (task instanceof ToDo) {
            return TaskType.TODO;
        } else if (task instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if (task instanceof Event) {
            return TaskType.EVENT;
        } else if (task instanceof FixedDurationTask) {
            return TaskType.FIXED_DURATION_TASK;
        }
        throw new IllegalArgumentException("Unknown task type: " + (task != null ? task.getClass() : "null"));
    }
}
