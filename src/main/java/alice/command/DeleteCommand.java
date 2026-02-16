package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        return tasks.deleteTask(input);
    }

}
