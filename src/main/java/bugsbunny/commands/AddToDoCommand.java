package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;
import bugsbunny.tasks.ToDo;

/**
 * Adds a {@link bugsbunny.tasks.ToDo} task to the list and saves the updated state.
 */
public class AddToDoCommand extends Command {
    private static final String TODO_USAGE = "Usage: todo <description>";

    public AddToDoCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        super.ensureValidArgs(super.args, AddToDoCommand.TODO_USAGE);
        Task t = new ToDo(super.args.trim());
        String output = tasks.addTask(t);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }
}
