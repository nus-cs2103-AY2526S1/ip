package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.FarewellCommand;
import com.neokortex.core.ApplicationContext;

/**
 * Creates an {@link FarewellCommand} based on the given {@link CommandRequest} and {@link ApplicationContext}
 *
 * <p>
 * The result is wrapped in a {@link FactoryResponse}
 * </p>
 *
 * @see FarewellCommand
 * @see ApplicationContext
 * @see CommandRequest
 */
public class FarewellCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new FarewellCommand();
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
