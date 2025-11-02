package buddy.command;

import buddy.exception.BuddyException;
import buddy.exception.EmptyDescriptionException;
import buddy.model.Event;
import buddy.model.Task;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class AddEventCommand implements Command {
    private final String desc;
    private final String from;
    private final String to;

    private AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /** Factory: parse "desc /from X /to Y" from rest of line. */
    public static AddEventCommand from(String rest) throws BuddyException {
        if (rest == null) {
            rest = "";
        }
        int fromIdx = rest.lastIndexOf("/from");
        int toIdx = rest.lastIndexOf("/to");
        if (fromIdx < 0 || toIdx < 0 || toIdx < fromIdx) {
            throw new BuddyException("Usage: event <desc> /from <from> /to <to>");
        }
        String desc = rest.substring(0, fromIdx).trim();
        String from = rest.substring(fromIdx + 5, toIdx).trim();
        String to = rest.substring(toIdx + 3).trim();
        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (from.isEmpty()) {
            throw new BuddyException("Event start time is missing. Usage: event <desc> /from <from> /to <to>");
        }
        if (to.isEmpty()) {
            throw new BuddyException("Event end time is missing. Usage: event <desc> /from <from> /to <to>");
        }
        return new AddEventCommand(desc, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(desc, from, to);
        tasks.add(t);
        ui.showMessage( "Got it. I've added this task:\n  " + t
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
