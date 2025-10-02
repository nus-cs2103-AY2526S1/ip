package com.neokortex.commands;

import com.neokortex.DialoguePath;
import com.neokortex.commands.factory.CompleteCommandFactory;
import com.neokortex.commands.factory.FactoryResponse;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.CommandResponse;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.commands.parsers.ParserResponse;

/**
 * Represents a generalised {@code CommandHanlder} that handles everything from instatntiating
 * to executing a command.
 *
 * <p>
 * The {@code CommandHanlder} breaks down Command handling into 5 parts:
 * </p>
 *
 * <ol>
 *     <li>Idle: Waiting for next input to handle</li>
 *     <li>Preprocessing: Parsing the input with the corresponding {@code CommandParser} and
 *     preparing for initialization</li>
 *     <li>Initialization: Process of using the corresponding {@code CommandFactory} to
 *     create the correct {@code Command}</li>
 *     <li>Execution: Executing the {@code Command}</li>
 *     <li>Completion: Cleanup after the {@code Command} has finished executing</li>
 * </ol>
 *
 * <p>
 * The {@code CommandHandler} handles these 5 stages by keeping track of a local enum. This enum
 * persists and adapts based on the current stage of processing. In the event that processing halts
 * at any given stage the stage where it stopped, the current state of the processing is preserved,
 * allowing the handler to resume processing once provided to suitable resources.
 * </p>
 *
 * <p>
 * At each stage, the handler also passes around a Response, such that when the process is terminated
 * at any point, a {@link Response} can be returned to handle it.
 * </p>
 * <p>
 * As of now, this powerful pattern has not been taken advantage off due to time constraints. There
 * are plans to make full use of partial {@code Command} processing in the future.
 * </p>
 *
 * @see CompleteCommandFactory
 * @see CompleteCommandParser
 * @see Response
 */
public class CommandHandler {
    private enum ProcessingStage {
        IDLE,
        PREPROCESSING,
        INITIALIZATION,
        EXECUTION,
        COMPLETION
    }

    private CompleteCommandParser parser;
    private CompleteCommandFactory factory;
    private CommandRequest currentRequest;
    private Command currentCommand;
    private CommandResponse latestCommandResponse;
    private ProcessingStage currentStage;

    /**
     * Constructs a {@code CommandHandler} out of the provided {@link CompleteCommandParser}
     * and {@link CompleteCommandFactory}
     *
     * @param parser the central parser that the {@code CommandHandler} should use.
     * @param factory the central factory that the {@code CommandHandler} should use.
     */
    public CommandHandler(CompleteCommandParser parser, CompleteCommandFactory factory) {
        this.currentStage = ProcessingStage.IDLE;
        this.parser = parser;
        this.factory = factory;
    }

    public void setParser(CompleteCommandParser parser) {
        this.parser = parser;
    }

    public void setFactory(CompleteCommandFactory factory) {
        this.factory = factory;
    }

    /**
     * interprets the input sent in by the {@code Chatbot} and returns a Response.
     * The {@code interpret} method can halt and resume processing back up at any stage
     * simply by calling it with a new input.
     *
     * @param input the input from the user
     * @return a {@link Response} representing the partial result of interpretation so far.
     */
    public Response interpret(String input) {
        return switch (this.currentStage) {
        case IDLE -> handleIdleStage(input);
        case PREPROCESSING -> handlePreprocessingStage(input);
        case INITIALIZATION -> handleInitializationStage(this.currentRequest, input);
        case EXECUTION -> handleExecutionStage(this.currentCommand, input);
        case COMPLETION -> handleCompletionStage(this.latestCommandResponse, input);
        default -> throw new IllegalStateException("Unexpected value: " + this.currentStage);
        };
    }

    private Response handleIdleStage(String input) {
        if (!this.parser.canParse(input)) {
            return this.handleTotalFailure();
        }
        this.currentStage = ProcessingStage.PREPROCESSING;
        return this.handlePreprocessingStage(input);

    }

    private Response handlePreprocessingStage(String input) {
        ParserResponse response = this.parser.parse(input);

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE
                || response.getStatus() == ResponseStatus.CATASTROPHIC_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentRequest = response.getResult();
            this.currentStage = ProcessingStage.INITIALIZATION;
            return handleInitializationStage(this.currentRequest, input);
        }

        return response;
    }

    private Response handleInitializationStage(CommandRequest request, String input) {
        FactoryResponse response = this.factory.handle(request);

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentCommand = response.getResult();
            this.currentStage = ProcessingStage.EXECUTION;
            return handleExecutionStage(this.currentCommand, input);
        }

        return response;
    }

    private Response handleExecutionStage(Command command, String input) {
        CommandResponse response = command.execute();

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentStage = ProcessingStage.COMPLETION;
            return handleCompletionStage(response, input);
        }

        return response;
    }

    private Response handleCompletionStage(CommandResponse response, String input) {
        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentStage = ProcessingStage.IDLE;
            return response;
        }

        return response;
    }

    private Response handleTotalFailure(Response response) {
        this.currentStage = ProcessingStage.IDLE;
        return response;
    }

    private Response handleTotalFailure() {
        return this.handleTotalFailure(new Response(DialoguePath.GENERIC_FAILURE, ResponseStatus.TOTAL_FAILURE));
    }
}
