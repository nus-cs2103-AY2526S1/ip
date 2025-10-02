package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Represents a centralised command parser that is capable of parsing any raw user input into a
 * {@link CommandRequest} based on the {@link CommandType}. It returns {@link CommandRequest} encapsulated
 * by the {@link ParserResponse} which is then sent off for the next stage of processing.
 *
 * <p>
 * The {@link CompleteCommandParser} utilises the {@link CommandType} enum class to match the {@link CommandType} to
 * its appropriate parser class.
 * </p>
 *
 * @see CommandType
 * @see CommandRequest
 * @see ParserResponse
 */
public class CompleteCommandParser {
    /**
     * Checks if a given input can be parsed based on whether it's command word can be found
     * within the list of aliases of commands.
     *
     * @param input raw input from the user
     * @return true if there is a valid CommandType associated, false otherwise.
     */
    public boolean canParse(String input) {
        return CommandType.getCommandType(input.split(" ", 2)[0]) != CommandType.INVALID;
    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input
     *
     * @param rawInput raw user input.
     * @return a {@link CommandRequest} object containing details regarding
     *         the specific command
     */
    public ParserResponse parse(String rawInput) {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            return new ParserResponse(DialoguePath.GENERIC_FAILURE, ResponseStatus.TOTAL_FAILURE);
        }

        String[] parts = rawInput.split("\\s+", 2);
        CommandType commandType = CommandType.getCommandType(parts[0]);
        return commandType.getParser().parse(commandType, rawInput);
    }
}
