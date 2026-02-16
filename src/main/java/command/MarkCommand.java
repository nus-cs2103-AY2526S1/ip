package command;

import ui.Lmbd;

/**
 * Represents a command to mark a specific task as done in the Lmbd application.
 * It requires the 1-based index of the task to be marked.
 */
public class MarkCommand extends Command {
    public MarkCommand() {
        super("mark", 1, "Marks the task associated with the number as done");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        try {

            int id = Integer.valueOf(args[0]) - 1;
            if (id < 0 || id >= lmbd.tasks.getTaskSize()) {
                return "Invalid id";
            }
            if (!lmbd.tasks.mark(id, true)) {
                return String.format("Task \"%s\" is already done, unable to mark", lmbd.tasks.getTaskTitle(id));
            }

            return String.format("The task \"%s\" has been marked as done.", lmbd.tasks.getTaskTitle(id));
        } catch (NumberFormatException e) {
            return "Expected a number";
        }
    }
}
