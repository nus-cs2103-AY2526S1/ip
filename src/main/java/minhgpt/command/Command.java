package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Encapsulate an user command and what to be executed.
 */
public abstract class Command {
    /** Map command regex String to constructor of the corresponding command. */
    protected static final HashMap<String, Supplier<Command>> registry = new HashMap<>();

    /**
     * Execute the program logic for the command.
     *
     * @param input Input from user.
     * @param taskList program's list of tasks.
     * @param ui program's ui drawer.
     * @param storage program's storage handler.
     */
    public abstract void execute(String input, TaskList taskList, Ui ui, Storage storage);

    public static void initialise() {
        new CommandAdd();
        new CommandBye();
        new CommandDelete();
        new CommandList();
        new CommandMark();
        new CommandUnmark();
    }

    /**
     * Factory method for creating commands.
     *
     * @param input Input command from user.
     */
    public static Command parseCommand(String input) {
        for (String regex : registry.keySet()) {
            if (input.matches(regex)) {
                return registry.get(regex).get();
            }
        }

        return new CommandAdd();
    }

    /**
     * Return true if 'command' is a CommandBye. False otherwise.
     */
    public static boolean isCommandBye(Command command) {
        return command instanceof CommandBye;
    }
}
