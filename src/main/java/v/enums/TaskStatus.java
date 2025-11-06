package v.enums;

/**
 * Represents the status of a task.
 * A task can be either done or not done.
 */
public enum TaskStatus {
    NOT_DONE(" "),
    DONE("X");

    private final String icon;

    TaskStatus(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon representing this status.
     *
     * @return The status icon ("X" for done, " " for not done).
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns the opposite status.
     *
     * @return DONE if current status is NOT_DONE, and vice versa.
     */
    public TaskStatus toggle() {
        return this == DONE ? NOT_DONE : DONE;
    }
}
