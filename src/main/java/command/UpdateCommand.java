package command;

import misc.TaskBotException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to update existing tasks
 */
public class UpdateCommand extends Command {
    private int index;
    private String field;
    private String newVal;

    /**
     * Initialises an update command with the specified task index and update info
     * @param index
     * @param field
     * @param newVal
     */
    public UpdateCommand(int index, String field, String newVal) {
        this.index = index;
        this.field = field;
        this.newVal = newVal;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        Task t = tasks.getTask(index - 1);
        switch (field.toLowerCase()) {
        case "desc":
            t.setDescription(newVal);
            break;
        case "by":
            if (t instanceof Deadline) {
                ((Deadline) t).setBy(newVal);
            } else {
                throw new TaskBotException("OOPS!! Task " + index + "is not a deadline.");
            }
            break;
        case "start":
            if (t instanceof Event) {
                ((Event) t).setStart(newVal);
            } else {
                throw new TaskBotException("OOPS!! Task " + index + "is not an event.");
            }
            break;
        case "end":
            if (t instanceof Event) {
                ((Event) t).setEnd(newVal);
            } else {
                throw new TaskBotException("OOPS!! Task " + index + "is not an event.");
            }
            break;
        default:
            throw new TaskBotException("OOPS!! I don't know what you want to update.");
        }
        storage.saveTasks(tasks.getTasks());
        return ui.showUpdate(t);
    }
}
