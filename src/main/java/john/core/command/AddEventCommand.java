package john.core.command;

import john.model.Event;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import java.time.LocalDateTime;

public class AddEventCommand implements Command {
    private final String desc;
    private final LocalDateTime from, to;

    public AddEventCommand(String desc, LocalDateTime from, LocalDateTime to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = new Event(desc, from, to);
        tasks.add(t);
        storage.save(tasks);
        return CommandResult.ok("Got it. I've added this task:\n" + t + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
