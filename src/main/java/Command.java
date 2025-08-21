// Referenced from: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html (Planets example)
public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    LIST("list"),
    HELP("help"),
    BYE("bye"),
    NONE("none");

    private final String commandWord;

    Command(String word) {
        this.commandWord = word;
    }

    public String getCommandWord() {
        return this.commandWord;
    }

    public static Command stringToCommand(String word) throws BBongException {
        for (Command c : Command.values()) {
            if (c.getCommandWord().equalsIgnoreCase(word)) return c;
        }
        throw new BBongException("Unknown Command");
    }
}
