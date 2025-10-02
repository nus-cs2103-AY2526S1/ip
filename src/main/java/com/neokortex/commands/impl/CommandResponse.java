package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;

/**
 * Represents the result of attempting to execute a {@link Command}. It directly inherits from {@link Response}
 * and tentatively doesn't provide any additional functionality, but it is nice to separate out its purpose from
 * the generic Response.
 *
 * @see DialoguePath
 * @see ResponseStatus
 */
public class CommandResponse extends Response {
    public CommandResponse(DialoguePath directive, ResponseStatus responseType) {
        super(directive, responseType);
    }
}
