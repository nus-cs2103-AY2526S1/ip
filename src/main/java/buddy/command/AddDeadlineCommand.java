package buddy.command;

import buddy.exception.BuddyException;
import buddy.exception.EmptyDescriptionException;
import buddy.model.Deadline;
import buddy.model.Task;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class AddDeadlineCommand implements Command {
    private final String desc;
    private final String by;

    private AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    /** Factory: parse "desc /by date" from rest of line. */
    public static AddDeadlineCommand from(String rest) throws BuddyException {
        if (rest == null) {
            rest = "";
        }
        int byIdx = rest.lastIndexOf("/by");
        if (byIdx < 0) {
            throw new BuddyException("Usage: deadline <desc> /by <date>");
        }
        String desc = rest.substring(0, byIdx).trim();
        String by   = rest.substring(byIdx + 3).trim();
        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (by.isEmpty())   {
            throw new BuddyException("Deadline date is missing. Usage: deadline <desc> /by <date>");
        }
        return new AddDeadlineCommand(desc, by);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        ui.showMessage( "Got it. I've added this task:\n  " + t
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
