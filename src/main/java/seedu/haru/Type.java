package seedu.haru;

/**
 * Represents the different types of tasks supported by the application.
 */
public enum Type {
    /** A task without a specific deadline or duration. */
    TODO,

    /** A task that must be completed by a specific date or time. */
    DEADLINE,

    /** A task that takes place during a specific time range. */
    EVENT
}
