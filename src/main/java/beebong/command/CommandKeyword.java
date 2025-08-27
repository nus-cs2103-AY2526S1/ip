package beebong.command;

import beebong.exception.UnknownCommandException;

// Referenced from: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html (Planets example)
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

    CommandKeyword(String word) {
        this.keyword = word;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public static CommandKeyword stringToCommand(String word) throws UnknownCommandException {
        for (CommandKeyword c : CommandKeyword.values()) {
            if (c.getKeyword().equalsIgnoreCase(word)) {
                return c;
            }
        }
        throw new UnknownCommandException();
    }
}
