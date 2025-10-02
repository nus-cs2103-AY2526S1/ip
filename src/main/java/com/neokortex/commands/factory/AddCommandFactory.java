package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.AddCommand;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.core.ApplicationContext;
import com.neokortex.task.DeadlineTask;
import com.neokortex.task.EventTask;
import com.neokortex.task.Task;
import com.neokortex.task.ToDoTask;
import com.neokortex.time.Time;

/**
 * Creates an {@link AddCommand} based on the given {@link CommandRequest} and {@link ApplicationContext}
 * <p>
 * An AddCommand can either create a {@code ToDoTask}, {@code DeadlineTask} or {@code EventTask}
 * This parser handles that accordingly.
 * </p>
 *
 * <p>
 * The result is wrapped in a {@link FactoryResponse}
 * </p>
 *
 * @see AddCommand
 * @see ApplicationContext
 * @see CommandRequest
 */
public class AddCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        return switch(request.getCommandType()) {
        case TODO -> createTodo(context, request);
        case DEADLINE -> createDeadline(context, request);
        case EVENT -> createEvent(context, request);
        default -> new FactoryResponse(DialoguePath.CATASTROPHIC_FAILURE, ResponseStatus.CATASTROPHIC_FAILURE);
        };
    }

    private FactoryResponse createTodo(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();
        Task todoTask = new ToDoTask(arguments[0].strip().replaceAll("\\s+", " "));
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), todoTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

    private FactoryResponse createDeadline(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();

        String description = arguments[0].strip().replaceAll("\\s+", " ");
        Time deadline = Time.parseTime(arguments[1]);

        if (deadline == null) {
            return new FactoryResponse(DialoguePath.INVALID_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        Task deadLineTask = new DeadlineTask(description, deadline);
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), deadLineTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

    private FactoryResponse createEvent(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();

        String description = arguments[0].strip().replaceAll("\\s+", " ");
        Time startTime = Time.parseTime(arguments[1]);
        Time endTime = Time.parseTime(arguments[2]);

        if (startTime == null || endTime == null) {
            return new FactoryResponse(DialoguePath.INVALID_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        Task eventTask = new EventTask(description, startTime, endTime);
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), eventTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

}
