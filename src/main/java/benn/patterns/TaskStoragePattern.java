package benn.patterns;


import java.util.regex.Pattern;

/**
 * Defines the regular expression patterns used to parse tasks
 * that have been saved to and loaded from persistent storage.
 *
 * <p>Each {@code Pattern} corresponds to a specific task type
 * (Todo, Deadline, or Event). These patterns are used by the
 * storage layer to deserialize lines in the save file back into
 * {@link benn.tasks.Task} objects.</p>
 *
 * <p>The expected storage formats are pipe-delimited:</p>
 * <ul>
 *   <li><b>Todo</b>: {@code T | <done> | <description>}</li>
 *   <li><b>Deadline</b>: {@code D | <done> | <description> | <by>}</li>
 *   <li><b>Event</b>: {@code E | <done> | <description> | <from> | <to>}</li>
 * </ul>
 *
 * <p>Where:</p>
 * <ul>
 *   <li>{@code <done>} is {@code 1} if the task is marked as completed, {@code 0} otherwise</li>
 *   <li>{@code <description>} is the task description</li>
 *   <li>{@code <by>} is the due date/time for a deadline task (in storage format, typically {@code dd/MM/yyyyTHH:mm})</li>
 *   <li>{@code <from>} and {@code <to>} are the start and end date/times for an event task</li>
 * </ul>
 */
public final class TaskStoragePattern {
    public static final Pattern TODO = Pattern.compile("^\\s*T\\s*\\|\\s*(?<done>[01])\\s*\\|\\s*(?<description>[^|]+)\\s*$");
    public static final Pattern DEADLINE = Pattern.compile("^\\s*D\\s*\\|\\s*(?<done>[01])\\s*\\|\\s*(?<description>[^|]+)\\s*\\|\\s*(?<by>[^|]+)\\s*$");
    public static final Pattern EVENT = Pattern.compile("^\\s*E\\s*\\|\\s*(?<done>[01])\\s*\\|\\s*(?<description>[^|]+)\\s*\\|\\s*(?<from>[^|]+)\\s*\\|\\s*(?<to>[^|]+)\\s*$");
}
