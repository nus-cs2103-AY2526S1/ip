package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidTodoException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.task.ToDo;
import evansbot.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty()) throw new InvalidTodoException();
        tasks.addTask(new ToDo(description));
    }
}
