package evansbot.command;

import evansbot.task.Deadline;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidDeadlineException;
import evansbot.ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty() || by.isEmpty()) throw new InvalidDeadlineException();
        tasks.addTask(new Deadline(description, by));
    }
}