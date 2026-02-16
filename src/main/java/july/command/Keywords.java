package july.command;

/**
 * Enumeration of valid command keywords for the July task management system.
 * This enum defines all supported command keywords and provides functionality
 * to parse user input and extract keywords along with their arguments.
 */
public enum Keywords {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    CHECK("check"),
    FIND("find"),
    FIX("fix");


    private final String keyword;

    /**
     * Constructs a keyword enum value with the specified string representation.
     *
     * @param s the string representation of the keyword
     */
    Keywords(String s) {
        this.keyword = s;
    }

    /**
     * Gets the string representation of this keyword.
     *
     * @return the keyword string
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Parses user input to extract the command keyword and remaining argument.
     * This method checks if the input starts with any valid keyword and splits
     * the input into the keyword and the remaining description/argument.
     *
     * @param input the user input string to parse
     * @return a String array where index 0 contains the matched keyword (or empty string
     *         if no match) and index 1 contains the remaining input after the keyword
     */
    public static String[] parse(String input) {
        for (Keywords k : values()) {
            if (input.startsWith(k.getKeyword())) {
                String description = input.substring(k.getKeyword().length());
                return new String[]{k.getKeyword(), description};
            }
        }
        return new String[]{"", input};
    }

}
