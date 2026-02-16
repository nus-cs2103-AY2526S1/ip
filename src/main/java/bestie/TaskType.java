package bestie;

/**
 * Enumerates the types of tasks supported by Bestie and their storage codes.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String shortCode;

    /**
     * Associates a task type with its single-character storage code.
     *
     * @param shortCode character used in the save file
     */
    TaskType(String shortCode) {
        this.shortCode = shortCode;
    }

    /**
     * Returns the single-character code persisted by {@link Storage}.
     *
     * @return storage-friendly discriminator
     */
    public String getShortCode() {
        return shortCode;
    }
}