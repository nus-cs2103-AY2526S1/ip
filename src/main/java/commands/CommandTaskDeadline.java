package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import app.Messages;
import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import tasks.Deadline;

/**
 * This command creates a new Deadline task.
 * The task is then added to the task list and saved.
 */
public class CommandTaskDeadline extends Command {
    private static final Map<String, List<String>> flagNames = Map.of(
            "by", List.of("by", "b"));

    private final Deadline deadline;
    private int taskSize;
    private String command;

    /**
     * Creates a Deadline command from the given user input.
     *
     * @param input Raw user input string
     * @throws BoopError if the task name or deadline is missing,
     *                   or if the date format is invalid
     */
    public CommandTaskDeadline(String input) throws BoopError {
        this.command = input;

        Flags flags = Flags.parseFlags(flagNames, input);

        if (!flags.has("")) {
            throw new BoopError(Messages.ERROR_NAME_NOT_GIVEN);
        }
        if (!flags.has("by")) {
            throw new BoopError(Messages.ERROR_DEADLINE_NOT_GIVEN);
        }

        try {
            deadline = new Deadline(
                    flags.get(""),
                    LocalDate.parse(flags.get("by")));
        } catch (DateTimeParseException e) {
            throw new BoopError(Messages.ERROR_INVALID_DATE_FORMAT);
        }
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        tasklist.addToList(deadline);
        tasklist.setStateChangeCommmandString(command);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_DEADLINE.formatted(deadline.toString(), taskSize);
    }
}
