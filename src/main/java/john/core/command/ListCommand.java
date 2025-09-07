package john.core.command;

import john.core.exception.ParseException;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

public final class ListCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        return CommandResult.ok(sb.isEmpty() ? "No tasks yet." : sb.toString().trim());
    }
}
