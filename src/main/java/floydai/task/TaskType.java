package floydai.task;

/**
 * Represents the type of {@link Task}.
 * <p>
 * Each task type has a unique single-letter icon used in string
 * representations of tasks.
 */
public enum TaskType {
    /** A simple to-do task (icon: {@code "T"}). */
    TODO("T"),

    /** A task with a deadline (icon: {@code "D"}). */
    DEADLINE("D"),

    /** A scheduled event task (icon: {@code "E"}). */
    EVENT("E");

    /** The single-letter icon associated with the task type. */
    private final String icon;

    /**
     * Constructs a {@code TaskType} with the specified icon.
     *
     * @param icon The single-letter icon representing the task type.
     */
    TaskType(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon associated with this task type.
     *
     * @return The single-letter icon of the task type.
     */
    public String getIcon() {
        return icon;
    }
}
