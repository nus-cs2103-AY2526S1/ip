package com.neokortex.commands.parsers;

import com.neokortex.commands.CommandType;

/**
 * Represents a wrapper for 2 fields:
 *
 * <ul>
 *     <li>{@link #commandType}: the {@link CommandType} of this command</li>
 *     <li>{@link #body}: the remaining command with excluding the Command word</li>
 * </ul>
 *
 * <p>It provides a convenient way to store a command word and command body</p>
 * <p>Primarily used during command parsing by the {@link CommandParser}s</p>
 *
 * @see CommandType
 * @see CommandParser
 */
public class CommandBody {
    private CommandType commandType;
    private String body;

    /**
     * Constructs a CommandBody based on the given {@link CommandType} and body
     *
     * @param commandType the {@link CommandType} of this command
     * @param body the body of the command (the input excluding the command word)
     */
    public CommandBody(CommandType commandType, String body) {
        this.commandType = commandType;
        this.body = body;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getBody() {
        return body;
    }
}
