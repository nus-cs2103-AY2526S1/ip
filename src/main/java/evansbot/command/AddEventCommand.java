package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidEventException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;
import evansbot.task.Event;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) throw new InvalidEventException();
        tasks.addTask(new Event(description, from, to));
    }
}