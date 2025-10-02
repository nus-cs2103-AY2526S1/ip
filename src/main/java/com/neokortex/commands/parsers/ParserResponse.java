package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;


/**
 * Represents the result of attempting to parse a command. Directly inherits from {@link Response}, but
 * additionally encapsulates a {@link CommandRequest}
 *
 * <p>
 * Primarily used by the {@link CommandParser}s to wrap the result of parsing
 * </p>
 *
 * @see CommandParser
 */
public class ParserResponse extends Response {
    private CommandRequest request;

    public ParserResponse(DialoguePath directive, ResponseStatus responseStatus) {
        super(directive, responseStatus);
    }

    public void setCommandRequest(CommandRequest request) {
        this.request = request;
    }

    public CommandRequest getResult() {
        return request;
    }
}
