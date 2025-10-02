package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Parses an {@code FindCommand}.
 *
 * <p>
 * A find Command has the following format:
 * </p>
 *
 * <ul>
 *     <li>find {String keyword}</li>
 * </ul>
 *
 * @see com.neokortex.commands.impl.FindCommand
 */
public class FindCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType commandType, String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        String[] tokens;
        ParserResponse response;

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_ID_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        tokens = new String[] {
                body.getBody()
        };

        response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(body.getCommandType(), input, tokens));
        return response;

    }
}
