package siri;

public enum Command {
    ECHO("echo"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    DELETE("delete"),
    FIND("find"),
    UNDO("undo"),
    BYE("bye");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * getter of keyword
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Get command from the keyword input
     * @param keyword keyword that the user input
     * @return the command associated with the keyword
     */
    public static Command fromKeyword(String keyword) {
        for (Command cmd : Command.values()) {
            if (cmd.keyword.equalsIgnoreCase(keyword)) {
                return cmd;
            }
        }
        return null;
    }

}
