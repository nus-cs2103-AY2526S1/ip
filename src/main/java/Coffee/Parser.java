package Coffee;

/**
 * Parses raw user input strings into executable {@link Command} instances.
 * The parser extracts the leading keyword (case-insensitive) and forwards the remaining
 * text as arguments to the appropriate command implementation.
 */
public class Parser {

    /**
     * Returns a {@link Command} corresponding to the given input line.
     * The input is split into two parts: the first token is treated as the command keyword,
     * and the remainder (if any) is passed as the command's arguments.
     * <ul>
     *   <li>{@code bye}       → {@link ByeCommand}</li>
     *   <li>{@code list}      → {@link ListCommand}</li>
     *   <li>{@code todo}      → {@link TodoCommand}</li>
     *   <li>{@code deadline}  → {@link DeadlineCommand}</li>
     *   <li>{@code event}     → {@link EventCommand}</li>
     *   <li>{@code delete}    → {@link DeleteCommand}</li>
     *   <li>{@code mark}      → {@link MarkCommand}</li>
     *   <li>{@code unmark}    → {@link UnmarkCommand}</li>
     *   <li>{@code find}      → {@link FindCommand}</li>
     *   <li>Any other keyword → {@link UnknownCommand}</li>
     * </ul>
     *
     * @param fullCommand full input line entered by the user. Must not be {@code null}.
     * @return a concrete {@link Command} ready to be executed.
     */
    public static Command parseCommand(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";
        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "event":
            return new EventCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "find":
            return new FindCommand(args);
        default:
            return new UnknownCommand(fullCommand);
        }
    }
}
