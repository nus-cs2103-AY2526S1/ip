package john.core.command;

import john.core.exception.ParseException;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

public class UnmarkCommand implements Command {
    private final int oneBased;
    public UnmarkCommand(int oneBased) { this.oneBased = oneBased; }
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        Integer idx = (oneBased - 1 >= 0 && oneBased - 1 < tasks.size()) ? oneBased - 1 : null;
        if (idx == null) return CommandResult.ok("Please provide a valid task number between 1 and " + tasks.size() + ".");
        tasks.get(idx).markAsIncomplete();
        storage.save(tasks);
        return CommandResult.ok("OK, I've marked this task as not done yet:\n" + tasks.get(idx));
    }
}