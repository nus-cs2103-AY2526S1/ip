package commands;

import java.util.Objects;

import ineffaexceptions.IneffaException;

/**
 * Contains methods that bot is able to perform.
 */
public enum CommandsEnum {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", null),
    UNMARK("unmark", null),
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E"),
    DELETE("delete", null),
    FIND("find", null),
    HELP("help", null);

    private final String commandName;
    private final String shortCode;
    /**
     * Contains methods that bot is able to perform.
     */
    CommandsEnum(String commandName, String shortCode) {
        this.commandName = commandName;
        this.shortCode = shortCode;
    }

    /**
     * Returns CmdType based on command passed by user.
     *
     * @param command string passed by user.
     * @return CmdType
     * @throws IneffaException If command type invalid
     */
    public static CommandsEnum fromString(String command) throws IneffaException {
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (Objects.equals(cmd.commandName, command)) {
                return cmd;
            }
        }
        throw new IneffaException("Invalid command");
    }

    /**
     * Returns CmdType based on short code of Task.
     *
     * @param code short code of task.
     * @return long code of task
     * @throws IneffaException If command type invalid
     */
    public static CommandsEnum fromShortCode(String code) throws IneffaException {
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (Objects.equals(cmd.shortCode, code)) {
                return fromString(cmd.commandName);
            }
        }
        throw new IneffaException("Invalid short code");
    }
}
