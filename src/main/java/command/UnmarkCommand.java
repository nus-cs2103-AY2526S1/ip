package command;

import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Represents marking a Task as incomplete.
 */
public class UnmarkCommand implements Command {

    private String idx;

    /**
     * Creates the unmark command to unmark a given task
     * @param idx index of task in the task list.
     */
    public UnmarkCommand(String idx) {
        this.idx = idx;
        assert idx != null : "please fill in the index.";
    }

    /**
     * Marks the task as not done.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) {
        int idxx = Integer.parseInt(idx) - 1;
        if (idxx < 0 || idxx >= tasks.getSize()) {
            String errMsg = "Cannot unmark task " + (idxx + 1) + ": no such task.";
            ui.showMsg(ui.format(errMsg));
            return errMsg;
        }
        tasks.unmark(idxx);
        String msg = "OK, I've marked this task as not done yet:\n  " + tasks.get(idxx).getDisplayString() + "\n";
        ui.showMsg(ui.format(msg));
        f.saveTasks(tasks);
        return msg;
    }

    /**
     * For the program to know when to exit the conversation with user.
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
