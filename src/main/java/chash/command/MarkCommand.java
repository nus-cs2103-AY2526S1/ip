package chash.command;

import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

import java.io.IOException;

/** Command to mark or unmark a task. */
public class MarkCommand extends Command {
    private final String index;
    private final boolean mark;

    /**
     * Creates a new mark/unmark command.
     *
     * @param index Task index (starts from 1, not 0 like arrays)
     * @param mark {@code true} to mark done, {@code false} to unmark
     */
    public MarkCommand(String index, boolean mark) {
        assert index != null;

        this.index = index;
        this.mark = mark;
    }

    /**
     * {@inheritDoc}
     * Mark/Unmark the indicated task and write updated tasklist to the db
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        assert tasks != null;
        assert ui != null;
        assert db != null;

        try {
            //Get Task specified by user and set its status accordingly
            Task task = tasks.get(Integer.parseInt(this.index) - 1).setDone(this.mark);

            //Write DB changes
            db.writeDb(tasks.getAll());

            //Print mark or unmark message
            ui.printMsg(
                (this.mark) ? 
                "Nice! I've marked this task as done:\n  " + task.toString() : 
                "OK, I've marked this task as not done yet:\n  " + task.toString()
            );
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            ui.printErr("Invalid index: " + this.index);
        } catch (IOException ex) {
            ui.printErr("Error accessing CHASHDB, all data in memory will be lost on exit");
        }
    }
}
