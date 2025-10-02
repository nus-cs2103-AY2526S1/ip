package com.neokortex.commands.parsers;

import com.neokortex.commands.CommandType;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.exceptions.MissingCommandException;

/**
 * Parses rawInput provided by the user based on the given {@link CommandType} and produces a CommandRequest.
 * the {@link CommandRequest} is then wrapped in a {@link ParserResponse} which provides additional data like the
 * chatbot response to the user and the processing status.
 *
 *
 * <p>
 * Each child class will provide its own implementation for parsing, but all child should attach a
 * {@link CommandRequest} upon successful parsing.
 * </p>
 *
 * <p>
 * The class also provides a utility function {@link #extractCommandBody(String)} which is provides a convenient
 * way to split the input into the command word and command body/ The result is wrapped in a
 * {@link com.neokortex.commands.parsers.CommandBody}
 * </p>
 *
 * @see com.neokortex.commands.CommandType
 * @see com.neokortex.commands.parsers.CommandBody
 * @see com.neokortex.commands.parsers.ParserResponse
 */
public abstract class CommandParser {
    /**
     * Parses an input based on the CommandType and returns a ParserResponse
     *
     * @param commandType the {@link CommandType} to parse into
     * @param input the input to parse
     * @return the ParserResponse which encapsulates the {@link CommandRequest} to be used
     *         in the next phase of command processing.
     */
    public abstract ParserResponse parse(CommandType commandType, String input);

    static CommandBody extractCommandBody(String input) throws MissingCommandException {
        String[] parts = input.split("\\s+", 2);

        if (parts[0].trim().isEmpty()) {
            throw new MissingCommandException("No command found");
        }

        CommandType commandType = CommandType.getCommandType(parts[0].trim());
        String body = "";
        if (parts.length == 2) {
            body = parts[1].trim();
        }

        return new CommandBody(commandType, body);
    }
}
