package ip.commands;

/**
 * Enum of commands available to be called by the user
 */
public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    REPEAT("repeat", new RepeatCommand()),
    TODO("todo", new AddToDoCommand()),
    DEADLINE("deadline", new AddDeadlineCommand()),
    EVENT("event", new AddEventCommand()),
    DELETE("delete", new DeleteCommand()),
    EXIT("bye", new ExitCommand()),
    FIND("find", new FindCommand()),
    SNOOZE("snooze", new SnoozeCommand());

    private final String keyword;
    private final Command command;

    CommandType(String keyword, Command command) {
        this.keyword = keyword;
        this.command = command;
    }

    /**
     * Returns the CommandType matching the user input
     *
     * @param input User Input
     * @return CommandType for the command matching user input
     */
    public static CommandType findCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return REPEAT;
        }

        for (CommandType type : values()) {
            if (input.equals(type.keyword) || input.startsWith(type.keyword + " ")) {
                return type;
            }
        }

        return REPEAT;
    }

    public Command getCommand() {
        return this.command;
    }
}
