package minhgpt.command;

import java.util.HashMap;
import java.util.function.Supplier;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulates an user command and what to be executed.
 */
public abstract class Command {

    /** Maps command regex String to constructor of the corresponding command. */
    private static final HashMap<String, Supplier<Command>> registry = new HashMap<>();

    /**
     * Puts mapping into 'registry'.
     *
     * @param regex    Regex that the command matches.
     * @param supplier Constructor for the command.
     */
    protected static void register(String regex, Supplier<Command> supplier) {
        assert (regex != null);
        assert (supplier != null);

        registry.put(regex, supplier);
    }

    /**
     * Executes the program logic for the command.
     *
     * @param input    Input from user.
     * @param taskList Program's list of tasks.
     * @param ui       Program's ui drawer.
     * @param storage  Program's storage handler.
     * @return Response to the command.
     */
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        assert (input != null);
        assert (taskList != null);
        assert (ui != null);
        assert (storage != null);
        return "";
    }

    /**
     * Initialises the mapping in 'registry'.
     *
     * This will call the "static {}" block in all Command classes.
     *
     * Must be called before using 'parseCommand'.
     */
    public static void initialise() {
        new CommandAdd();
        new CommandBye();
        new CommandDelete();
        new CommandList();
        new CommandMark();
        new CommandUnmark();
        new CommandFind();
        new CommandSort();
    }

    /**
     * Parses user input and return the corresponding command.
     *
     * Must call 'initialise' before using this method.
     *
     * @param input Input command from user.
     */
    public static Command parseCommand(String input) {
        for (String regex : registry.keySet()) {
            if (input.matches(regex)) {
                return registry.get(regex).get();
            }
        }

        return new CommandInvalid();
    }
}
