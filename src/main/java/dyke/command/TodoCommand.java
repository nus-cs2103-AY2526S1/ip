package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Todo;
import dyke.ui.Ui;

/**
 * Houses methods for {@code TodoCommand}
 */
public class TodoCommand extends Command {
    private final String[] inputs;

    /**
     * Creates {@code TodoCommand}
     * @param inputs Inputs from User
     */
    public TodoCommand(String[] inputs) {
        super(CommandType.TODO);
        this.inputs = inputs;

    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = handleTodo(library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleTodo(Library library) throws DykeException {
        if (inputs.length < 2) {
            throw new DykeException("Phineas, I don't know what we gonna do today!");
        }
        String description = inputs[1].trim();
        Todo todo = new Todo(description);
        return library.addTask(todo);
    }
}
