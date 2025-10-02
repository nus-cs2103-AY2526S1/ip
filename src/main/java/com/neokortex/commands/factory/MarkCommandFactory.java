package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.MarkCommand;
import com.neokortex.core.ApplicationContext;

/**
 * Creates an {@link MarkCommand} based on the given {@link CommandRequest} and {@link ApplicationContext}
 *
 * <p>
 * The result is wrapped in a {@link FactoryResponse}
 * </p>
 *
 * @see MarkCommand
 * @see ApplicationContext
 * @see CommandRequest
 */
public class MarkCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new MarkCommand(context.getStorage(), context.getTaskList(),
                Integer.parseInt(request.getTokens()[0]));
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
