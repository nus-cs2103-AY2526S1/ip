package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Deadline;
import alice.task.Event;
import alice.task.Todo;

public class EditCommand extends Command {
    private final String input;

    public EditCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        return tasks.editTask(input);
    }

}
