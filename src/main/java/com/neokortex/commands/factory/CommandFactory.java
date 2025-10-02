package com.neokortex.commands.factory;

import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.core.ApplicationContext;

/**
 * Creates a command from the given {@link CommandRequest} based on the given {@link ApplicationContext}.
 * The command is then wrapped in a {@link FactoryResponse} which provides additional data like the chatbot response
 * and the processing status.
 *
 * <p>
 * Each child class will provide its own implementation for parsing, but all child should attach a
 * {@link CommandRequest} upon successful parsing.
 * </p>
 *
 * @see ApplicationContext
 * @see Command
 * @see CommandRequest
 * @see FactoryResponse
 */
public abstract class CommandFactory {
    public abstract FactoryResponse create(ApplicationContext context, CommandRequest request);
}
