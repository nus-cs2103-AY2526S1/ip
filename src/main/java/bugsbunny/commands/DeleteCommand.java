package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Deletes a task from the list and saves the updated state.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_USAGE = "Usage: delete <task index>";

    public DeleteCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        super.ensureValidArgs(super.args, DeleteCommand.DELETE_USAGE);
        int index = Parser.parseInteger(super.args) - 1;
        if (index < 0 || index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }
        String output = tasks.deleteTask(index);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }
}
