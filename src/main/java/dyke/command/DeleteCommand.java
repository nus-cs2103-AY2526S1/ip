package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.ui.Ui;

/**
 * Houses methods for {@code DeleteCommand}
 */
public class DeleteCommand extends Command {
    private static final int ACCOUNT_INDEX = 1;
    private final String[] inputs;

    /**
     * Creates {@code DeleteCommand}
     * @param inputs Inputs from User
     */
    public DeleteCommand(String[] inputs) {
        super(CommandType.DELETE);
        this.inputs = inputs;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = library.deleteTask(handleDelete());
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private int handleDelete() throws DykeException {
        if (inputs.length < 2) {
            throw new DykeException("I think you forgot the"
                    + " description...");
        }
        return Integer.parseInt(inputs[1]) - ACCOUNT_INDEX;
    }
}
