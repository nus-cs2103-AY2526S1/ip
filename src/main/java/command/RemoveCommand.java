package command;

import task.Task;
import ui.Lmbd;

/**
 * Represents a command to remove a task from the Lmbd application's task list.
 * It requires the 1-based index of the task to be removed.
 */
public class RemoveCommand extends Command {
    public RemoveCommand() {
        super("remove", 1, "Removes the task from the list with the associated number");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        try {
            int id = Integer.valueOf(args[0]) - 1;
            if (id < 0 || id >= lmbd.tasks.getTaskSize()) {
                return "Invalid id";
            }
            Task t = lmbd.tasks.removeTask(id);

            return String.format("The task \"%s\" has been removed.", t.getTaskTitle());
        } catch (NumberFormatException e) {
            return "Expected a number";
        }

    }
}
