package chiikawa.command;

import chiikawa.ChiikawaException;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.Task;

/**
 * Class for marking a task as complete.
 */
public class MarkCommand extends Command {
    private String indexStr;

    /**
     * Constructor that takes in a String representation of the rest of the user's command.
     *
     * @param command String representation of the rest of the user's command.
     */
    public MarkCommand(String command) {
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

            Task markedTask = tasks.markTask(index);
            return ui.showMark(markedTask);
        } catch (NumberFormatException e) {
            throw new ChiikawaException("giv 1 numba!! 1!! number!!! only!!!!");
        }
    }
}
