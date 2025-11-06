package v.enums;

/**
 * Represents the type of a task.
 * Each task type has a corresponding tag used in string representation.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String tag;

    TaskType(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the single-character tag for this task type.
     *
     * @return The task type tag ("T" for Todo, "D" for Deadline, "E" for Event).
     */
    public String getTag() {
        return tag;
    }
}
