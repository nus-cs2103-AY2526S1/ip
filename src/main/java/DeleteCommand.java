import Exceptions.InvalidTaskIndexException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskIndexException {
        tasks.deleteTask(index);
    }
}

