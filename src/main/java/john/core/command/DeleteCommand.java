package john.core.command;

import john.core.exception.ParseException;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

public class DeleteCommand implements Command {
    private final int oneBased;
    public DeleteCommand(int oneBased) { this.oneBased = oneBased; }
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        int idx = oneBased - 1;
        if (idx < 0 || idx >= tasks.size())
            return CommandResult.ok("Please provide a valid task number between 1 and " + tasks.size() + ".");
        Task removed = tasks.remove(idx);
        storage.save(tasks);
        return CommandResult.ok("Noted. I've removed this task:\n" + removed + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
