package beebong.command;

import beebong.exception.UnknownCommandException;

// Referenced from: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html (Planets example)
/**
 * Represents the set of valid command keywords supported by the application.
 * Provides mapping between string inputs and corresponding {@code CommandKeyword} values.
 */
public enum CommandKeyword {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    LIST("list"),
    FIND("find"),
    HELP("help"),
    BYE("bye");

    private final String keyword;

    /**
     * Constructs a {@code CommandKeyword} with its associated string keyword.
     *
     * @param word the new command's keyword.
     */
    CommandKeyword(String word) {
        this.keyword = word;
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Converts a string to the corresponding {@code CommandKeyword}.
     *
     * @param word the string to convert.
     * @return the matching {@code CommandKeyword}.
     * @throws UnknownCommandException if the string does not match any valid command's keyword.
     */
    public static CommandKeyword stringToCommand(String word) throws UnknownCommandException {
        for (CommandKeyword c : CommandKeyword.values()) {
            if (c.getKeyword().equalsIgnoreCase(word)) {
                return c;
            }
        }
        throw new UnknownCommandException();
    }
}
