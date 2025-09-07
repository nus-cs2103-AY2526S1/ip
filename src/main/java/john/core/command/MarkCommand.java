package john.core.command;

import john.core.exception.ParseException;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

public class MarkCommand implements Command {
    private final int oneBased;

    public MarkCommand(int oneBased) {
        this.oneBased = oneBased;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        Integer idx = toZeroBasedOrNull(oneBased, tasks.size());
        if (idx == null) {
            return CommandResult.ok("Please provide a valid task number between 1 and " + tasks.size() + ".");
        }
        tasks.get(idx).markAsComplete();
        storage.save(tasks);
        return CommandResult.ok("Nice! I've marked this task as done:\n" + tasks.get(idx));
    }

    private static Integer toZeroBasedOrNull(int oneBased, int size) {
        int z = oneBased - 1;
        return (z >= 0 && z < size) ? z : null;
    }
}