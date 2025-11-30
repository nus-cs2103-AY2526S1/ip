package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Event;
import dyke.ui.Ui;

/**
 * Houses methods for {@code EventCommand}
 */
public class EventCommand extends Command {
    private final String[] inputs;

    /**
     * Creates {@code EventCommand}
     * @param inputs Inputs from User
     */
    public EventCommand(String[] inputs) {
        super(CommandType.EVENT);
        this.inputs = inputs;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = handleEvent(library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleEvent(Library library) throws DykeException {
        if (inputs.length != 2) {
            throw new DykeException("What is this event about?");
        }

        // Split on /from to get dyke.tasks.Event and Period
        String[] fromSplit = inputs[1].split("/from", 2);
        String activity = fromSplit[0].trim();

        // Checking if From day-time given
        if (fromSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen."
                    + "\n\t format: /from *day-time* /to *day-time*");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();

        // Checking if To day-time given
        if (toSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen."
                    + "\n\t format: /from *day-time* /to *day-time*");
        }

        String to = (toSplit.length > 1) ? toSplit[1].trim() : "";

        Event event = new Event(activity, from, to);
        return library.addTask(event);
    }
}
