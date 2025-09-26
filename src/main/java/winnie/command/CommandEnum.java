package winnie.command;

/**
 * The enum type that represents the different types of commands.
 */
public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    SNOOZE("snooze"),
    UNKNOWN("");

    private final String commandWord;

    CommandEnum(String commandWord) {
        this.commandWord = commandWord;
    }

    public static CommandEnum fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return UNKNOWN;
        }

        String command = input.trim().split("\\s+")[0].toLowerCase();

        for (CommandEnum c : CommandEnum.values()) {
            if (c.commandWord.equals(command)) {
                return c;
            }
        }
        return UNKNOWN;
    }
}
