package com.neokortex.commands.factory;

import com.neokortex.commands.CommandType;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.core.ApplicationContext;

/**
 * Represents a centralised command factory that is capable of creating any command from the given CommandRequest.
 * It returns a Command which is encapsulated in a {@link FactoryResponse} which is then sent off to the next stage
 * of command processing.
 *
 * <p>
 * The {@link CompleteCommandFactory} utilises the {@link CommandType} enum class to match the {@link CommandType} to
 * its appropriate factory class.
 * </p>
 *
 * @see CommandType
 * @see com.neokortex.commands.impl.Command
 * @see FactoryResponse
 */
public class CompleteCommandFactory {
    private ApplicationContext context;
    /**
     * Constructs a new CommandParser with initial state.
     */
    public CompleteCommandFactory(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input, wrapped in a {@link FactoryResponse}
     *
     * @param request raw user input.
     * @return a {@link FactoryResponse} object containing details regarding
     *         the specific command
     */
    public FactoryResponse handle(CommandRequest request) {
        CommandType commandType = request.getCommandType();
        return commandType.getFactory().create(context, request);
    }
}
