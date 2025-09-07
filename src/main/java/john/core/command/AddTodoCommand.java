package john.core.command;

import john.core.exception.ParseException;
import john.model.Task;
import john.model.TaskList;
import john.model.Todo;
import john.ports.Storage;
import john.ports.Ui;

public class AddTodoCommand implements Command {
    private final String desc;
    public AddTodoCommand(String desc) { this.desc = desc; }
    @Override public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = new Todo(desc);
        tasks.add(t); storage.save(tasks);
        return CommandResult.ok("Got it. I've added this task:\n" + t + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
