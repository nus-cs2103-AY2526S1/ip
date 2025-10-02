package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;

/**
 * Echoes the user's input text back.
 *
 * <p>
 * The {@code EchoCommand} takes user input and displays it back to the user.
 * </p>
 *
 * @see Command
 */
public class EchoCommand implements Command {
    private final String echo;

    /**
     * Constructs a new EchoCommand with the text to echo.
     *
     * @param echo the text to echo
     * @throws NullPointerException if either context or request is null
     */
    public EchoCommand(String echo) {
        this.echo = echo;
    }


    @Override
    public CommandResponse execute() {
        CommandResponse response = new CommandResponse(DialoguePath.ECHO, ResponseStatus.SUCCESS);
        response.attachResults(new String[]{this.echo});
        return response;
    }
}
