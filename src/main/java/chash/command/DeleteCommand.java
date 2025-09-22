package chash.command;

import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

import java.io.IOException;

/** Command to delete a task. */
public class DeleteCommand extends Command {
    private final String index;

    /**
     * Creates a delete command for a given index.
     *
     * @param index Task index (starts from 1, not 0 like arrays)
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Delete the indicated task and write updated tasklist to the db
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        try {
            //Delete task
            Task task = tasks.remove(Integer.parseInt(this.index) - 1);

            //Write DB changes
            db.writeDb(tasks.getAll());

            ui.printMsg(String.format(
                    "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    task,
                    tasks.size()
            ));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            ui.printErr("Invalid index: " + this.index);
        } catch (IOException ex) {
            ui.printErr("Error accessing CHASHDB, all data in memory will be lost on exit");
        }
    }
}
