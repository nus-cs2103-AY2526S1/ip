package chash.command;

import chash.exception.ChashException;
import chash.parser.TaskParser;
import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

import java.io.IOException;

/** Command to add a new task. */
public class AddCommand extends Command {
    private final CommandTypeEnum type;
    private final String args;

    /**
     * Creates a new add command.
     *
     * @param type Command type (TODO/DEADLINE/EVENT)
     * @param args Task arguments (e.g. description, by, from, to)
     */
    public AddCommand(CommandTypeEnum type, String args) {
        assert type != null;
        assert args != null;

        this.type = type;
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * Add the indicated task and write updated tasklist to the db
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        assert tasks != null;
        assert ui != null;
        assert db != null;

        //Create task
        Task task;
        try {
            task = TaskParser.fromChashString(this.type, this.args);
        } catch (ChashException ex) {
            ui.printErr(ex.getMessage());
            return;
        }

        //Check if task is dupe
        int i = 0;
        for (var tmp : tasks.getAll()) { //var allowed for auto type deduction by compiler
            i += 1;
            if (task.exportString().equals(tmp.exportString())) {
                //Print notice that it might be duplicate
                ui.printErr("Your new task might have already been added, refer to task #" + i);
                break;
            }
        }

        //Add task
        tasks.add(task);

        //Write db
        try {
            db.writeDb(tasks.getAll());
        } catch (IOException ex) {
            ui.printErr("Error accessing CHASHDB, all data in memory will be lost on exit");
        }

        //Print
        ui.printMsg(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                tasks.size()
        ));
    }
}
