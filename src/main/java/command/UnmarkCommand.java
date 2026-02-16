package command;

import ui.Lmbd;

/**
 * Represents a command to unmark a specific task as not done (incomplete) in
 * the Lmbd application. It requires the 1-based index of the task to be
 * unmarked.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand() {
        super("unmark", 1, "Unmarks the task associated with the number as done");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        try {

            int id = Integer.valueOf(args[0]) - 1;
            if (id < 0 || id >= lmbd.tasks.getTaskSize()) {
                return "Invalid id";
            }
            if (!lmbd.tasks.mark(id, true)) {
                return String.format("Task \"%s\" is not done, unable to unmark", lmbd.tasks.getTaskTitle(id));
            }

            return String.format("The task \"%s\" has been unmarked.", lmbd.tasks.getTaskTitle(id));
        } catch (NumberFormatException e) {
            return "Expected a number";
        }
    }
}
