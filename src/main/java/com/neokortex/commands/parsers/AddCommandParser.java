package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Parses an {@code AddCommand}. An {@code AddCommand} can either result in the creation of a {@code ToDoTask}, a
 * {@code DeadlineTask} or an {@code EventTask}. The type of task it is parsed into depends on the CommandType.
 *
 * <p>
 * An add Command has the following formats:
 * </p>
 *
 * <ol>
 *     <li>Todo Format : todo {String taskDescription}</li>
 *     <li>Deadline Format : deadline {String taskDescription} /by {String time}</li>
 *     <li>Event Format : event {String taskDescription} /from {String time} /to {String time}</li>
 * </ol>
 *
 * @see com.neokortex.commands.impl.AddCommand
 */
public class AddCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType type, String input) {
        return switch(type) {
        case TODO -> parseTodo(input);
        case DEADLINE -> parseDeadline(input);
        case EVENT -> parseEvent(input);
        default -> new ParserResponse(DialoguePath.CATASTROPHIC_FAILURE, ResponseStatus.CATASTROPHIC_FAILURE);
        };
    }

    private ParserResponse parseTodo(String input) {
        CommandBody body = CommandParser.extractCommandBody(input);

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        String[] tokens = new String[] {
                body.getBody().trim()
        };

        ParserResponse response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(CommandType.TODO, input, tokens));
        return response;
    }

    private ParserResponse parseDeadline(String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        String[] tokens;

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        tokens = body.getBody().split("/by");

        if (tokens[0].isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }


        if (tokens.length == 1) {
            return new ParserResponse(DialoguePath.NO_DEADLINE_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        if (tokens.length > 2) {
            return new ParserResponse(DialoguePath.TOO_MANY_TIMES_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        ParserResponse response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(CommandType.DEADLINE, input, tokens));
        return response;
    }

    private ParserResponse parseEvent(String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        String[] tokens;

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }


        String[] parts = body.getBody().split("/from");

        if (parts[0].trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        if (parts.length == 1) {
            return new ParserResponse(DialoguePath.NO_START_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        if (parts.length > 2) {
            return new ParserResponse(DialoguePath.TOO_MANY_TIMES_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        String[] timings = parts[1].split("/to");

        if (timings.length == 1) {
            return new ParserResponse(DialoguePath.NO_END_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        if (timings.length > 2) {
            return new ParserResponse(DialoguePath.TOO_MANY_TIMES_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        tokens = new String[] {
                parts[0].trim(),
                timings[0].trim(),
                timings[1].trim()
        };

        ParserResponse response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(CommandType.EVENT, input, tokens));
        return response;
    }
}
