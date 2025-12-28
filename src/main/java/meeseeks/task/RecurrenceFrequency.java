package meeseeks.task;

/**
 * Enum representing different recurrence frequencies for recurring tasks.
 */
public enum RecurrenceFrequency {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    YEARLY("yearly");

    private final String displayName;

    RecurrenceFrequency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Parses a string to get the corresponding RecurrenceFrequency.
     * 
     * @param frequency the frequency string to parse
     * @return the corresponding RecurrenceFrequency
     * @throws IllegalArgumentException if the frequency is not recognized
     */
    public static RecurrenceFrequency fromString(String frequency) {
        assert frequency != null : "Frequency string cannot be null";
        
        String lowerFrequency = frequency.toLowerCase().trim();
        for (RecurrenceFrequency freq : values()) {
            if (freq.displayName.equals(lowerFrequency)) {
                return freq;
            }
        }
        throw new IllegalArgumentException("Unknown recurrence frequency: " + frequency);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
