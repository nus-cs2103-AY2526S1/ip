package dyke.parse;

/**
 * Provides a list of recognized commands
 */
public enum CommandType {
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    BYE("BYE!"),
    LIST("LIST"),
    MARK("MARK"),
    DELETE("DELETE"),
    UNKNOWN("UNKNOWN"),
    HELP("HELP"),
    FIND("FIND"),
    WHAT("WHAT?"),
    STATS("STATS");

    private final String keyword;

    CommandType(String s) {
        this.keyword = s;
    }

    /**
     * Parses command inputs into recognized {code CommandType}s.
     * @param s User input.
     * @return {@code CommandType}
     * @throws DykeException If input is unrecognized.
     */
    public static CommandType fromString(String s) throws DykeException {
        for (CommandType commandType : values()) {
            if (commandType.keyword.equals(s.toUpperCase())) {
                return commandType;
            }
        }
        throw new DykeException(" Unknown command: " + s);
    }
}
