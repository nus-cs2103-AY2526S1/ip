import Exceptions.EvansBotException;
import Exceptions.InvalidDeadlineException;

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