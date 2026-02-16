package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code unmark} command which marks a task in the current
 * task list as not done.
 * Example:
 *     unmark 3
 * marks the third task in the list as not done.
 */
public class UnmarkCommand implements Command {

    /** The raw user input string containing the unmark command. */
    private final String input;

    /**
     * Constructs an {@code UnmarkCommand} with the specified input string.
     *
     * @param input the raw user input that starts with {@code unmark}
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code unmark} command.
     * Parses the task index from the input string, marks the corresponding
     * {@link Task} as not done in the {@link TaskList}, saves the updated list
     * to {@link Storage}, and displays a confirmation message through the
     * {@link Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to save the updated task list
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the input format is invalid or missing the index
     * @throws NumberFormatException if the index cannot be parsed as an integer
     * @throws IndexOutOfBoundsException if the index does not correspond to an existing task
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new TaroException("Opps!: Usage: mark <index> or unmark <index>");
        }
        int idx = Integer.parseInt(parts[1]);

        tasks.get(idx - 1).markAsUndone();
        Task t = tasks.get(idx - 1);

        ui.showLine();
        ui.show(" OK, I've marked this task as not done yet:");
        ui.show("   " + t);

        storage.save(tasks);
        return false;
    }
}
