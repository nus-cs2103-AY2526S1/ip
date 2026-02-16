package kane;

/**
 * Represents the different types of tasks supported in the Kane application.
 * Each task type is associated with a short code used for file storage and display.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the short code representing this task type.
     *
     * @return the short string representation of this task type
     */
    public String getCode() {
        return code;
    }
}
