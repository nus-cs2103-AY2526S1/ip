package commands;

import exceptions.DawaeException;
import storage.Storage;
import taskstuff.Task;
import taskstuff.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final String args;
    
    /** {@inheritDoc} */
    public UnmarkCommand(String args) {
        this.args = args;
    }
    
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        try {
            int idx = Integer.parseInt(args.trim()) - 1;
            Task t = tasks.get(idx);
            t.unmarkDone();
            storage.save(tasks);
            return ui.showUnmarked(t, tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DawaeException("brAhHHH invalid task number provided");
        }
    }
}
