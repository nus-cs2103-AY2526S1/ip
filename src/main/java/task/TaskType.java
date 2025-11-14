package task;

/**
 * Represents the type of a {@link Task} in the Baymax application.
 * <p>
 * There are three types of tasks:
 * <ul>
 *     <li>{@link #TODO} - a simple task without a time constraint</li>
 *     <li>{@link #DEADLINE} - a task that has a specific due date</li>
 *     <li>{@link #EVENT} - a task that has a start and end date</li>
 * </ul>
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}
