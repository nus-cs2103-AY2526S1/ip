package moon.commands.enums;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents all supported commands in the Moon chatbot.
 * <p>
 * Each command has:
 * <ul>
 *   <li>a keyword entered by the user,</li>
 *   <li>a usage format string (for error/help messages), and</li>
 *   <li>a status code returned to the backend for control flow.</li>
 * </ul>
 */
public enum Command {
    /** List all current tasks. */
    LIST("list", "list", 2),

    /** Mark a task as done. */
    MARK("mark", "mark {task number}", 4),

    /** Mark a task as not done. */
    UNMARK("unmark", "unmark {task number}", 5),

    /** Add a todo task. */
    TODO("todo", "todo {task description}", 6),

    /** Add a deadline task with a due time. */
    DEADLINE("deadline",
            "deadline {task description} /by dd/mm/yyyy HHMM {deadline time}",
            7),

    /** Add an event task with start and end times. */
    EVENT("event",
            "event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}",
            8),

    /** Delete a task by its index. */
    DELETE("delete", "delete {task number}", 9),

    /** Find a task using keywords. */
    FIND("find", "find {keyword}", 10);

    private final String keyword;
    private final String format;
    private final int statusCode;

    /**
     * Constructs a command enum constant.
     *
     * @param keyword    User input keyword that triggers this command
     * @param format     Usage format shown in error/help messages
     * @param statusCode Status code returned after executing this command
     */
    Command(String keyword, String format, int statusCode) {
        this.keyword = keyword;
        this.format = format;
        this.statusCode = statusCode;
    }

    /**
     * Returns the keyword used to trigger this command.
     *
     * @return Keyword string
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Returns the usage format for this command.
     *
     * @return Format string
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * Returns the status code associated with this command.
     *
     * @return Status code integer
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * Returns a stream of all defined commands.
     *
     * @return Stream of {@link Command} values
     */
    // return a stream version of the list of Command objects.
    public static Stream<Command> getCommandStream() {
        return Arrays.stream(Command.values());
    }
}
