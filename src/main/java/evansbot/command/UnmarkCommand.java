package evansbot.command;

import evansbot.Exceptions.InvalidTaskIndexException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskIndexException {
        try {
            tasks.unmarkTask(index);
        } catch (InvalidTaskIndexException e) {
            ui.showError("Invalid task number! Please enter a number between 1 and " + e.getMaxIndex());
        }
    }
}