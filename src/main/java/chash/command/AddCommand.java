package chash.command;

import chash.exception.ChashException;
import chash.parser.TaskParser;
import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

import java.io.IOException;

public class AddCommand extends Command {
    private final CommandTypeEnum type;
    private final String args;

    public AddCommand(CommandTypeEnum type, String args) {
        this.type = type;
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        //Create task
        Task task;
        try {
            task = TaskParser.fromChashString(this.type, this.args);
        } catch (ChashException ex) {
            ui.printErr(ex.getMessage());
            return;
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
