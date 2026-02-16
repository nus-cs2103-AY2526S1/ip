package commands;

import java.util.HashMap;
import java.util.Map;

import core.TaskList;
import util.Storage;

/**
 * Initializes and provides Command objects based on user input.
 * When chatbot is constructed, all command objects are created and stored in a map for easy retrieval.
 * These command objects are used to map user input strings to their corresponding command implementations.
 * This approach eliminates the need for multiple if-else or switch-case statements to
 * determine which command to execute.
 */
public class CommandFactory {
    private final Map<String, Command> commands;

    /**
     * Constructor for CommandFactory class.
     * @param taskList TaskList object that stores the list of tasks
     * @param storage Storage object that handles loading and saving tasks to persistent storage
     */
    public CommandFactory(TaskList taskList, Storage storage) {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        
        this.commands = new HashMap<>();
        initializeCommands(taskList, storage);
        
        assert this.commands != null : "Commands map should not be null after initialization";
    }

    /**
     * Initializes the command mappings.
     * @param taskList TaskList object that stores the list of tasks.
     * @param storage Storage object that handles loading and saving tasks to persistent storage.
     */
    private void initializeCommands(TaskList taskList, Storage storage) {
        commands.put("bye", new ExitCommand(taskList, storage));
        commands.put("list", new ListCommand(taskList, storage));
        commands.put("mark", new MarkCommand(taskList, storage));
        commands.put("unmark", new UnmarkCommand(taskList, storage));
        commands.put("delete", new DeleteCommand(taskList, storage));
        commands.put("todo", new TodoCommand(taskList, storage));
        commands.put("event", new EventCommand(taskList, storage));
        commands.put("deadline", new DeadlineCommand(taskList, storage));
        commands.put("find", new FindCommand(taskList, storage));
        commands.put("sort", new SortCommand(taskList, storage));
    }

    /**
     * @param commandType Command type as a string (e.g., "todo", "event", "deadline", "list", "mark", "unmark",
     *                    "delete", "find", "sort", "bye").
     * @return Corresponding Command object, or null if the command type is invalid.
     */
    public Command getCommand(String commandType) {
        assert commandType != null : "Command type should not be null";
        
        return commands.get(commandType.toLowerCase());
    }
}
