package betty.task;

/**
 * Represents the Priority enum class that can get priority levels of the enums
 */
public enum Priority {
    LOW("LOW", 2),
    MEDIUM("MEDIUM", 1),
    HIGH("HIGH", 0),
    NONE("NONE", 3);

    private final String label;
    private final int rank;
    Priority(String label, int level) {
        this.label = label;
        this.rank = level;
    }

    public static Priority getPriority(String priority) {
        return switch(priority.toLowerCase()) {
        case "low" -> LOW;
        case "medium" -> MEDIUM;
        case "high" -> HIGH;
        default -> NONE;
        };
    }
    @Override
    public String toString() {
        return this.label;
    }

    public int getRank() {
        return this.rank;
    }
}
