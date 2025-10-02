package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;

/**
 * Represents the result of attempting to create a command from a {@link CommandFactory}. It Directly
 * inherits from {@link Response} but additionally encapsulates a {@link Command}
 *
 * <p>
 * Primarily used by the {@link CommandFactory}s to wrap the result of creating new {@link Command}s
 * </p>
 *
 * @see CommandFactory
 */
public class FactoryResponse extends Response {
    private Command result;

    public FactoryResponse(DialoguePath directive, ResponseStatus responseType) {
        super(directive, responseType);
    }

    public void setResult(Command command) {
        this.result = command;
    }

    public Command getResult() {
        return this.result;
    }
}
