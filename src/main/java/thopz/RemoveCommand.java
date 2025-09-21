package thopz;

import java.io.IOException;

/**
 * Remove a task from the tasklist
 */

public class RemoveCommand extends Command {

    private final int no;

    /**
     * Constructs a command to remove specified tasks
     *
     * @param no  the task number to be removed
     *
     *
     */

    public RemoveCommand(int no) {
        this.no = no;
    }

    @Override
    public String perform(TaskList ls, Storage storage, Ui ui) {
        if (ls.size() == 0) {
            throw new IllegalArgumentException("No more tasks to delete!");
        }

        if (no >= 1 && no <= ls.size()) {

            Task removed = ls.removeTask(no);
            try {
                storage.save(ls.getAllTasks());
            } catch (IOException e) {
                System.out.println("Cannot save changes");
            }
            return ui.showRemoval(removed, ls.size());

        }
        return null;
    }

}
