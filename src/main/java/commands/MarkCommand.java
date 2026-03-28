package commands;

import exceptions.DawaeException;
import storage.Storage;
import taskstuff.Task;
import taskstuff.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final String args;
    
    /**
     * Creates a done command with raw arguments (usually the 1-based task index).
     *
     * @param args the raw argument string from the user input.
     */
    public MarkCommand(String args) {
        this.args = args;
    }
    
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        try {
            int idx = Integer.parseInt(args.trim()) - 1;
            Task t = tasks.get(idx);
            t.markDone();
            storage.save(tasks);
            return ui.showMarked(t, tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DawaeException("brAhHHH invalid task number provided");
        }
    }
}
