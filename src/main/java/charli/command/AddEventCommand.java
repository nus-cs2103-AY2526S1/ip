package charli.command;

import charli.exception.CharliException;
import charli.task.Event;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to add a new event task with start and end time to tasklist.
 * Handles the 'show' command for adding time-bound events.
 */
public class AddEventCommand implements Command {

    private static final String USAGE = "Use: show [event] /from [start time] /to [end time]";

    private final String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {

        // Split into command + rest, so "show  ...".
        String[] headTail = fullCommand.trim().split("\\s+", 2);
        if (headTail.length < 2 || headTail[1].isBlank()) {
            throw new CharliException(USAGE);
        }
        String args = headTail[1]; // everything after "show"

        int fromPos = args.indexOf("/from");
        int toPos   = args.indexOf("/to");

        if (fromPos < 0 || toPos < 0 || toPos < fromPos) {
            throw new CharliException(USAGE);
        }

        String description = args.substring(0, fromPos).trim();
        String from        = args.substring(fromPos + 5, toPos).trim(); // 5 = len("/from")
        String to          = args.substring(toPos + 3).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CharliException(USAGE);
        }

        try {
            tasks.add(new Event(description, from, to));
        } catch (Exception e) {
            throw new CharliException(USAGE);
        }

        return "Icon, I added this show to your schedule:\n"
                + tasks.getLastTaskString()
                + "\n\nNow you have " + tasks.size() + " tracks in your rotation!";
    }

    public boolean isExit() {
        return false;
    }
}
