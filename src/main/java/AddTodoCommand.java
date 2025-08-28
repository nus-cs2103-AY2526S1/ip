import Exceptions.EvansBotException;
import Exceptions.InvalidTodoException;

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
