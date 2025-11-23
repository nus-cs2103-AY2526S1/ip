package stewie.command;

import java.time.LocalDateTime;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.EventTask;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to add a event task.
 */
public class EventCommand implements Command {
    private static final String USAGE_MESSAGE = "event <description> /from <dd/MM/yyyy> <HH:mm> "
                                                + "/to <dd/MM/yyyy> <HH:mm>";
    private final String args;

    /**
     * Creates a new event command with the given arguments.
     *
     * @param args The command arguments containing task description, start time, and end time.
     */
    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the event command to add a new event task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        EventTask task = parseArgsToEventTask(args);
        String response = taskList.addTask(task);
        storage.saveTasks(taskList);

        assert response != null : "Final response should not be null";
        return response;
    }

    /**
     * Parses the given arguments to create an {@link EventTask}.
     *
     * @param args The command arguments containing the event description,
     *             start time, and end time.
     * @return A new {@link EventTask} with the given details.
     * @throws InvalidCommandException If the format is invalid, missing required fields,
     *                                 or date parsing fails.
     */
    public static EventTask parseArgsToEventTask(String args) throws InvalidCommandException {
        String[] parts = args.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new InvalidCommandException(USAGE_MESSAGE);
        }
        LocalDateTime startTime = Helper.parseDateTime(parts[1].trim());
        LocalDateTime endTime = Helper.parseDateTime(parts[2].trim());
        if (startTime == null || endTime == null) {
            throw new InvalidCommandException(USAGE_MESSAGE);
        }
        return new EventTask(parts[0].trim(), startTime, endTime);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.EVENT;
    }
}
