package minhgpt.command;

import java.util.HashMap;
import java.util.function.Supplier;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate an user command and what to be executed.
 */
public abstract class Command {

    /** Map command regex String to constructor of the corresponding command. */
    private static final HashMap<String, Supplier<Command>> registry = new HashMap<>();

    /**
     * Put mapping into 'registry'.
     *
     * @param regex    Regex that the command matches.
     * @param supplier Constructor for the command.
     */
    protected static void register(String regex, Supplier<Command> supplier) {
        registry.put(regex, supplier);
    }

    /**
     * Execute the program logic for the command.
     *
     * @param input    Input from user.
     * @param taskList Program's list of tasks.
     * @param ui       Program's ui drawer.
     * @param storage  Program's storage handler.
     * @return Response to the command.
     */
    public abstract String execute(String input, TaskList taskList, Ui ui, Storage storage);

    /**
     * Initialise the mapping in 'registry'.
     */
    public static void initialise() {
        new CommandAdd();
        new CommandBye();
        new CommandDelete();
        new CommandList();
        new CommandMark();
        new CommandUnmark();
        new CommandFind();
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
}
