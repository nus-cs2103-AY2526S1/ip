package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Parses an {@code DeleteCommand}.
 *
 * <p>
 * A delete Command has the following format:
 * </p>
 *
 * <ul>
 *     <li>delete {int taskId}</li>
 * </ul>
 *
 * @see com.neokortex.commands.impl.DeleteCommand
 */
public class DeleteCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType commandType, String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        String[] tokens;
        ParserResponse response;

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_ID_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        int taskID;
        try {
            taskID = Integer.parseInt(body.getBody());
        } catch (NumberFormatException e) {
            return new ParserResponse(DialoguePath.INVALID_ARGUMENTS, ResponseStatus.TOTAL_FAILURE);
        }

        if (taskID < 0) {
            return new ParserResponse(DialoguePath.INVALID_ARGUMENTS, ResponseStatus.TOTAL_FAILURE);
        }

        tokens = new String[] {
                body.getBody()
        };

        response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(body.getCommandType(), input, tokens));
        return response;

    }
}
