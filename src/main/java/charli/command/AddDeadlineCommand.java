package charli.command;

import charli.exception.CharliException;
import charli.task.Deadline;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

import java.time.format.DateTimeParseException;

/**
 * Command to add a new deadline task.
 * Syntax: drop <song> /by d/M/yyyy HHmm  (e.g., 2/12/2019 1800)
 */
public class AddDeadlineCommand implements Command {

    private static final String FLAG_BY = "/by";
    private static final String USAGE =
            "Use: drop <song> /by d/M/yyyy HHmm (e.g., 2/12/2019 1800)";

    private final String fullCommand;

    public AddDeadlineCommand(String fullCommand) {
        assert fullCommand != null : "fullCommand must not be null";
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        assert tasks != null : "tasks must not be null";

        // slice off the command word ("drop") safely
        String args = sliceArgs(fullCommand);
        if (args.isBlank()) {
            throw new CharliException(USAGE);
        }

        // require "/by" and split into description | by
        String[] pair = splitRequired(args, FLAG_BY, USAGE);
        String description = pair[0];
        String by = pair[1];

        if (description.isEmpty() || by.isEmpty()) {
            throw new CharliException(USAGE);
        }

        try {
            tasks.add(new Deadline(description, by));
        } catch (DateTimeParseException ex) {
            // user error → friendly message
            throw new CharliException("Invalid date/time. " + USAGE);
        }

        // build success message
        String last = tasks.get(tasks.size() - 1).toString();
        return "Sweettt! Added this upcoming drop:\n"
                + last + "\n\n"
                + "Now you have " + tasks.size() + " tracks in your rotation!";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /** Returns the part after the command word (e.g., after "drop"). */
    private static String sliceArgs(String raw) {
        String s = raw.trim();
        int space = s.indexOf(' ');
        if (space < 0) { return ""; } // no args were provided
        return s.substring(space + 1).trim();
    }

    /** Split once on a required flag; throws with usage if flag missing or either side empty. */
    private static String[] splitRequired(String args, String flag, String usage)
            throws CharliException {
        int i = args.indexOf(flag);
        if (i < 0) { throw new CharliException(usage); }
        String left = args.substring(0, i).trim();
        String right = args.substring(i + flag.length()).trim();
        if (left.isEmpty() || right.isEmpty()) { throw new CharliException(usage); }
        return new String[]{ left, right };
    }
}

