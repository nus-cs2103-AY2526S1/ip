package jackbot;

/**
 * Parses raw user input lines into structured {@link Parser.Result} commands
 * for the Jackbot program
 *
 * <p>Supported commands:
 * <ul>
 *   <li><b>bye</b> &rarr; {@link Type#BYE}</li>
 *   <li><b>list</b> &rarr; {@link Type#LIST}</li>
 *   <li><b>mark &lt;index&gt;</b> &rarr; {@link Type#MARK}</li>
 *   <li><b>unmark &lt;index&gt;</b> &rarr; {@link Type#UNMARK}</li>
 *   <li><b>delete &lt;index&gt;</b> &rarr; {@link Type#DELETE}</li>
 *   <li><b>todo &lt;text&gt;</b> &rarr; {@link Type#TODO}</li>
 *   <li><b>deadline &lt;text&gt;</b> &rarr; {@link Type#DEADLINE}</li>
 *   <li><b>event &lt;text&gt;</b> &rarr; {@link Type#EVENT}</li>
 * </ul>
 *
 * <p>Notes:
 * <ul>
 *   <li>Parsing is case-insensitive for command keywords.</li>
 *   <li>Indices are parsed as 1-based integers for mark/unmark/delete.</li>
 *   <li>Empty or whitespace-only input returns {@link Type#LIST} (no-op in UI).</li>
 * </ul>
 *
 */
public class Parser {

    /** Creates a new {@code Parser}. */
    public Parser() { }

    /**
     * The high-level command types recognized by the parser.
     */
    public enum Type {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
    }

    /**
     * Immutable parse result returned by {@link #parse(String)}.
     * <p>
     * Exactly one of {@code text} (for text-bearing commands) or {@code index}
     * (for index-bearing commands) is meaningful depending on {@link #type}.
     *
     */
    public static class Result {
        /** The parsed command type. */
        public final Type type;
        /** Command payload for {@link Type#TODO}, {@link Type#DEADLINE}, {@link Type#EVENT}. */
        public final String text;
        /** 1-based index for {@link Type#MARK}, {@link Type#UNMARK}, {@link Type#DELETE}. */
        public final int index;

        private Result(Type type, String text, int index) {
            this.type = type;
            this.text = text;
            this.index = index;
        }

        /**
         * Factory for results with only a {@link Type}.
         *
         * @param t the command type
         * @return a {@code Result} with empty text and index {@code -1}
         */
        public static Result of(Type t) {
            return new Result(t, "", -1);
        }

        /**
         * Factory for text-bearing commands (TODO/DEADLINE/EVENT).
         *
         * @param t the command type
         * @param s the payload text (not trimmed here)
         * @return a {@code Result} carrying {@code s}
         */
        public static Result text(Type t, String s) {
            return new Result(t, s, -1);
        }

        /**
         * Factory for index-bearing commands (MARK/UNMARK/DELETE).
         *
         * @param t the command type
         * @param i 1-based index of the task
         * @return a {@code Result} carrying {@code i}
         */
        public static Result index(Type t, int i) {
            return new Result(t, "", i);
        }
    }

    /**
     * Parses a raw input line into a {@link Result}.
     *
     * <p>Behavior:
     * <ul>
     *   <li>{@code null} or blank input &rarr; {@link Result#of(Type) of(LIST)}</li>
     *   <li>Case-insensitive command matching (e.g., {@code "BYE"} is accepted)</li>
     *   <li>Index arguments are parsed as integers via {@link Integer#parseInt(String)}</li>
     * </ul>
     *
     * @param rawInput the user input; may be {@code null}
     * @return the parsed {@link Result}
     * @throws JackbotException if the command is unknown or an index cannot be parsed
     */
    public Result parse(String rawInput) throws JackbotException {
        String input = rawInput == null ? "" : rawInput.trim();
        if (input.isEmpty()) {
            // Treat empty as no-op (stay in loop)
            return Result.of(Type.LIST);
        }

        // Fast paths
        if (equalsIgnoreCase(input, "bye"))  return Result.of(Type.BYE);
        if (equalsIgnoreCase(input, "list")) return Result.of(Type.LIST);

        // Commands with arguments
        if (startsWithIgnoreCase(input, "mark "))     return Result.index(Type.MARK,     parseIndex(input.substring(5)));
        if (startsWithIgnoreCase(input, "unmark "))   return Result.index(Type.UNMARK,   parseIndex(input.substring(7)));
        if (startsWithIgnoreCase(input, "delete "))   return Result.index(Type.DELETE,   parseIndex(input.substring(7)));

        if (startsWithIgnoreCase(input, "todo "))     return Result.text(Type.TODO,      input.substring(5).trim());
        if (startsWithIgnoreCase(input, "deadline ")) return Result.text(Type.DEADLINE,  input.substring(9).trim());
        if (startsWithIgnoreCase(input, "event "))    return Result.text(Type.EVENT,     input.substring(6).trim());
        if (startsWithIgnoreCase(input, "find "))     return Result.text(Type.FIND,      input.substring(5).trim());

        throw new JackbotException("Command doesn't exist");
    }

    // ----- helpers -----

    /**
     * Case-insensitive equality test.
     */
    private boolean equalsIgnoreCase(String a, String b) {
        return a.equalsIgnoreCase(b);
    }

    /**
     * Case-insensitive prefix test.
     */
    private boolean startsWithIgnoreCase(String s, String prefix) {
        return s.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * Parses a 1-based task index.
     *
     * @param s string containing an integer
     * @return the parsed integer
     * @throws JackbotException if parsing fails
     */
    private int parseIndex(String s) throws JackbotException {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            throw new JackbotException("Failed to parse task index number");
        }
    }
}
