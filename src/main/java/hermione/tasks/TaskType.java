package hermione.tasks;

/**
 * Enumeration representing the different types of tasks in the Hermione
 * application.
 * Each task type has an associated code used for serialization and storage.
 */
public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E"), FIXED_DURATION_TASK("F");

    private final String code;

    /**
     * Constructs a TaskType with the specified code.
     *
     * @param code The single-character code representing this task type.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Gets the TaskType corresponding to the given code.
     *
     * @param code The code to look up.
     * @return The TaskType with the matching code.
     * @throws IllegalArgumentException If no TaskType matches the given code.
     */
    public static TaskType fromCode(String code) {
        for (TaskType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid task type code: " + code);
    }

    /**
     * Gets the code associated with this task type.
     *
     * @return The single-character code for this task type.
     */
    public String getCode() {
        return code;
    }
}
