package goober.task;

import java.io.Serializable;

/**
 * Represents the priority level of a task. Provides helpers to parse priority from user input and to obtain a short
 * single-letter tag for display purposes.
 */
public enum Priority implements Serializable {
    HIGH, MEDIUM, LOW, NONE;

    /**
     * Parses a priority from a string.
     * Accepts common forms such as numbers (1, 2, 3) and words like "high", "medium", or "low". Null or blank input
     * returns NONE.
     *
     * @param s input string representing a priority
     * @return the parsed priority level
     * @throws IllegalArgumentException if the input is not recognized
     */
    public static Priority of(String s) {
        if (s == null || s.isBlank()) {
            return NONE;
        }
        switch (s.trim().toLowerCase()) {
        case "1":
        case "h":
        case "hi":
        case "high":
            return HIGH;
        case "2":
        case "m":
        case "med":
        case "medium":
            return MEDIUM;
        case "3":
        case "l":
        case "lo":
        case "low":
            return LOW;
        case "0":
        case "n":
        case "none":
            return NONE;
        default:
            throw new IllegalArgumentException("Unknown priority: " + s);
        }
    }

    /**
     * Returns a short single-letter tag suitable for compact displays. HIGH: "H", MEDIUM: "M", LOW: "L", NONE: empty
     * string.
     *
     * @return the short tag for this priority
     */
    public String shortTag() {
        switch (this) {
        case HIGH:
            return "H";
        case MEDIUM:
            return "M";
        case LOW:
            return "L";
        case NONE:
            return " ";
        default:
            return "";
        }
    }
}
