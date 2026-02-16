/**
 * Enum of valid command keywords recognized by the chatbot,
 * such as LIST, MARK, UNMARK, DELETE, DEADLINE, and EVENT.
 */

package salah;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    FIND("find");

    /* The string keyword associated with the command. */
    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Returns the string keyword associated with this command.
     *
     * @return the keyword of the command
     */
    public String getKeyword() {
        return keyword;
    }
}
