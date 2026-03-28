package alex;

/**
 * Represents the types of commands supported by the chatbot.
 * Each <code>CommandType</code> corresponds to a keyword (e.g., <code>todo</code>, <code>list</code>)
 * or a user-defined alias.
 */
public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    HELLO("hello"),
    BYE("bye"),
    ALIAS("alias"),
    UNKNOWN("unknown");

    private String keyword;

    /**
     * Constructs a <code>CommandType</code> with the specified keyword.
     *
     * @param keyword String keyword that represents the command.
     */
    CommandType(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the keyword associated with this command type.
     *
     * @return Keyword string of the command.
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Converts the specified string input into the corresponding <code>CommandType</code>,
     * taking into account both standard keywords and user-defined aliases.
     *
     * @param input     The user input string to be matched against commands.
     * @param aliasList Alias object containing user-defined command aliases.
     * @return The corresponding <code>CommandType</code>, or <code>UNKNOWN</code> if no match is found.
     */
    public static CommandType stringToEnum(String input, Alias aliasList) {
        for (CommandType c : CommandType.values()) {
            if (c.keyword.equalsIgnoreCase(input)) {
                return c;
            }
        }
        for (CommandType type : CommandType.values()) {
            String alias = aliasList.getAlias(type);
            if (alias != null && alias.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    /**
     * Converts the specified string input into the corresponding <code>CommandType</code>
     * based only on predefined command keywords.
     *
     * @param input The user input string to be matched against commands.
     * @return The corresponding <code>CommandType</code>, or <code>UNKNOWN</code> if no match is found.
     */
    public static CommandType stringToEnum(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.keyword.equalsIgnoreCase(input)) {
                return c;
            }
        }
        return UNKNOWN;
    }
    // Above method was done with the help of AI
}
