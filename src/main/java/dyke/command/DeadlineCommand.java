package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Deadline;
import dyke.ui.Ui;

/**
 * Houses methods for {@code DeadlineCommand}
 */
public class DeadlineCommand extends Command {
    private final String[] inputs;

    /**
     * Creates {@code DeadlineCommand}
     * @param inputs Inputs from User
     */
    public DeadlineCommand(String[] inputs) {
        super(CommandType.DEADLINE);
        this.inputs = inputs;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = handleDeadline(library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleDeadline(Library library) throws DykeException {
        if (inputs.length < 2) {
            throw new DykeException("I think you forgot the description...");
        }

        // Split input string with "/by"
        String[] parts = inputs[1].split("/by", 2);

        // Checking if day-time is given
        if (parts.length != 2 || parts[1].isBlank()) {
            throw new DykeException("When should this be done by?");
        }

        String activity = parts[0].trim();
        String by = parts[1].trim();

        Deadline deadline = new Deadline(activity, by);
        return library.addTask(deadline);
    }
}
