package sam.task;

/**
 * Represents the priority levels for tasks.
 * Tasks can have different priority levels to help users organize their work.
 */
public enum Priority {
    LOW(1, "LOW"),
    MEDIUM(2, "MEDIUM"), 
    HIGH(3, "HIGH");

    private final int level;
    private final String displayName;

    /**
     * Constructor for Priority enum.
     * @param level The numeric priority level (1-3)
     * @param displayName The display name for the priority
     */
    Priority(int level, String displayName) {
        this.level = level;
        this.displayName = displayName;
    }

    /**
     * Gets the numeric priority level.
     * @return The priority level (1-3)
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the display name of the priority.
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Parses a priority from a string input.
     * @param input The input string (can be "1", "2", "3", "low", "medium", "high")
     * @return The corresponding Priority enum value
     * @throws IllegalArgumentException If the input is invalid
     */
    public static Priority fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return MEDIUM; // Default priority
        }
        
        String normalized = input.trim().toLowerCase();
        
        switch (normalized) {
            case "1":
            case "low":
                return LOW;
            case "2":
            case "medium":
                return MEDIUM;
            case "3":
            case "high":
                return HIGH;
            default:
                throw new IllegalArgumentException("Invalid priority: " + input + 
                    ". Use 1/low, 2/medium, or 3/high");
        }
    }

    /**
     * Returns the string representation of the priority.
     * @return The display name of the priority
     */
    @Override
    public String toString() {
        return displayName;
    }
}
