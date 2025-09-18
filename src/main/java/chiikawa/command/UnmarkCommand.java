package chiikawa.command;

import chiikawa.ChiikawaException;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.Task;

/**
 * Class for unmarking a task from complete to incomplete.
 */
public class UnmarkCommand extends Command {
    private String indexStr;

    /**
     * Constructor that takes in a String representation of the rest of the user's command.
     *
     * @param command String representation of the rest of the user's command.
     */
    public UnmarkCommand(String command) {
        this.indexStr = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException {
        if (this.indexStr.isEmpty()) {
            throw new ChiikawaException("you give me nothin!! delete wat?!");
        }

        try {
            int index = Integer.parseInt(this.indexStr);

            if (index > Task.getTaskCount() || index <= 0) {
                throw new ChiikawaException("no more, wat u doin!!");
            }

            Task unmarkedTask = tasks.unmarkTask(index);
            return ui.showUnmark(unmarkedTask);
        } catch (NumberFormatException e) {
            throw new ChiikawaException("giv 1 numba!! 1!! number!!! only!!!!");
        }
    }
}
