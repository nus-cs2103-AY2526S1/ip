package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.SaveCommand;
import com.neokortex.core.ApplicationContext;

/**
 * Creates an {@link SaveCommand} based on the given {@link CommandRequest} and {@link ApplicationContext}
 *
 * <p>
 * The result is wrapped in a {@link FactoryResponse}
 * </p>
 *
 * @see SaveCommand
 * @see ApplicationContext
 * @see CommandRequest
 */
public class SaveCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new SaveCommand(
                context.getStorage(), context.getTaskList(), request.getTokens()[0].trim());
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
