package commands;

import java.util.List;
import java.util.Map;

import app.Messages;
import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import tasks.Event;

/**
 * This command creates a new Event task.
 * The task is then added to the task list and saved.
 */
public class CommandTaskEvent extends Command {
    private static final Map<String, List<String>> flagNames = Map.of(
            "from", List.of("from", "f"),
            "to", List.of("to", "t"));

    private final Event event;
    private int taskSize;
    private String command;

    /**
     * Creates a Event command from the given user input.
     *
     * @param input Raw user input string
     * @throws BoopError if the task name or start and end times is missing
     */
    public CommandTaskEvent(String input) throws BoopError {
        this.command = input;

        Flags flags = Flags.parseFlags(flagNames, input);

        if (!flags.has("")) {
            throw new BoopError(Messages.ERROR_NAME_NOT_GIVEN);
        }
        if (!flags.has("from")) {
            throw new BoopError(Messages.ERROR_STARTTIME_NOT_GIVEN);
        }
        if (!flags.has("to")) {
            throw new BoopError(Messages.ERROR_ENDTIME_NOT_GIVEN);
        }

        event = new Event(
                flags.get(""),
                flags.get("from"),
                flags.get("to"));
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        tasklist.addToList(event);
        tasklist.setStateChangeCommmandString(command);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_EVENT.formatted(event.toString(), taskSize);
    }
}
