package commands;

import exceptions.DawaeException;
import exceptions.DawaeMissingArgumentException;
import storage.Storage;
import taskstuff.Deadline;
import taskstuff.Task;
import taskstuff.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private final String args;
    
    /**
     * Creates a deadline-adding command with description and {@code /by} timestamp.
     *
     * @param args the raw argument string containing description and time.
     */
    public DeadlineCommand(String args) { this.args = args; }
    
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        // "desc /by 2025-08-31T06:30:00"
        if (args.isEmpty()) throw new DawaeMissingArgumentException("Brah, give me ur task description...\uD83D\uDE44");
        String[] parts = args.split("/by", 2);
        if (parts.length == 1) throw new DawaeMissingArgumentException("Brah, give me ur DEADLINE...\uD83D\uDE44");
        String desc = parts[0].trim();
        String by = parts[1].trim();
        Task t = new Deadline(desc, by);
        tasks.addTask(t);
        storage.save(tasks);
        return ui.showAdded(t, tasks.size());
    }
}
