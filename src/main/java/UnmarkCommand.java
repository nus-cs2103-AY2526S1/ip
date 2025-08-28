import Exceptions.InvalidTaskIndexException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskIndexException {
        tasks.unmarkTask(index);
    }
}