package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Unmarks a task and saves the updated state.
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_USAGE = "Usage: unmark <task index>";

    public UnmarkCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        super.ensureValidArgs(super.args, UnmarkCommand.UNMARK_USAGE);

        int index = Parser.parseInteger(super.args) - 1;
        if (index < 0 || index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }
        String output = tasks.unmarkTask(index);
        output += super.saveOrAppendError(tasks, ui, storage);
        return output;
    }
}
