package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Marks a task as done and saves the updated state.
 */
public class MarkCommand extends Command {
    private static final String MARK_USAGE = "Usage: mark <task index>";

    public MarkCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        super.ensureValidArgs(super.args, MarkCommand.MARK_USAGE);
        int index = Parser.parseInteger(super.args) - 1;
        if (index < 0 || index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        String output = tasks.markTask(index);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }
}
