package charsiew.task;

/**
 * Represents the different types of tasks that can exist in the CharSiew application.
 * Each task type is associated with a short string code used for storage and parsing.
 *
 * <p>The supported task types are:</p>
 * <ul>
 *   <li>{@link #TODO} - A basic to-do task, stored with code "T".</li>
 *   <li>{@link #DEADLINE} - A task with a deadline date, stored with code "D".</li>
 *   <li>{@link #EVENT} - A task with a start and end date, stored with code "E".</li>
 * </ul>
 *
 * <p>This enum provides methods to:</p>
 * <ul>
 *   <li>Retrieve the code of a task type via {@link #getCode()}.</li>
 *   <li>Look up a task type from its code string via {@link #fromCode(String)}.</li>
 * </ul>
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    /**
     * Constructs a TaskType with the specified code.
     *
     * @param code The short string representation of the task type (e.g., "T", "D", "E").
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code string associated with this task type.
     *
     * @return The task type code (e.g., "T", "D", "E").
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the TaskType corresponding to the given code.
     *
     * @param code The code string to look up (e.g., "T", "D", "E").
     * @return The matching TaskType.
     * @throws IllegalArgumentException If the code does not correspond to any valid task type.
     */
    public static TaskType fromCode(String code) {
        for (TaskType type : TaskType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown task type code: " + code);
    }
}
