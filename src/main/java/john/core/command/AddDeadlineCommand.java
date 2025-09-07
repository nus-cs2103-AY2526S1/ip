package john.core.command;

import john.core.exception.ParseException;
import john.model.Deadline;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand implements Command {
    private final String desc;
    private final LocalDateTime by;

    public AddDeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        storage.save(tasks);
        return CommandResult.ok("Got it. I've added this task:\n" + t + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}