package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.ListCommand;
import com.neokortex.core.ApplicationContext;

/**
 * Creates an {@link ListCommand} based on the given {@link CommandRequest} and {@link ApplicationContext}
 *
 * <p>
 * The result is wrapped in a {@link FactoryResponse}
 * </p>
 *
 * @see ListCommand
 * @see ApplicationContext
 * @see CommandRequest
 */
public class ListCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new ListCommand(context.getTaskList());
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
