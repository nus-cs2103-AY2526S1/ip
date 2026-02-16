package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.task.Todo;
import taro.ui.Ui;

/**
 * Represents the {@code todo} command which adds a {@link Todo} task
 * to the current task list.
 * Example:
 *     todo buy groceries
 * adds a todo task with the description "buy groceries".
 */
public class TodoCommand implements Command {
    /** The raw user input string containing the todo command. */
    private final String input;

    /**
     * Constructs a {@code TodoCommand} with the specified input string.
     *
     * @param input the raw user input that starts with {@code todo}
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code todo} command.
     * Extracts the task description from the input string, creates a new
     * {@link Todo} task, adds it to the {@link TaskList}, saves the updated
     * list to {@link Storage}, and displays a confirmation message through
     * the {@link Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to save the updated task list
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the task description is empty
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new TaroException("Opps!: The description of a todo cannot be empty.");
        }

        Task t = new Todo(desc, false);
        tasks.add(t);
        storage.save(tasks);

        ui.showLine();
        ui.show(" Got it. I've added this task:");
        ui.show("   " + t);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return false;
    }
}
