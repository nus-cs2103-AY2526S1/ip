package commands;

import java.util.Map;

import app.Messages;
import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import tasks.Todo;

/**
 * This command creates a new Todo task.
 * The task is then added to the task list and saved.
 */
public class CommandTaskTodo extends Command {
    private final Todo todo;
    private int taskSize;
    private String command;

    /**
     * Creates a Todo command from the given user input.
     *
     * @param input Raw user input string
     * @throws BoopError if the task name is missing
     */
    public CommandTaskTodo(String input) throws BoopError {
        this.command = input;

        Flags flags = Flags.parseFlags(Map.of(), input);

        if (!flags.has("")) {
            throw new BoopError(Messages.ERROR_NAME_NOT_GIVEN);
        }

        todo = new Todo(flags.get(""));
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        tasklist.addToList(todo);
        tasklist.setStateChangeCommmandString(command);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_TODO.formatted(todo.toString(), taskSize);
    }
}
