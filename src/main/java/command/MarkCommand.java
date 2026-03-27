package command;

import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Representing marking a task as complete.
 */
public class MarkCommand implements Command {

    private String idx;

    /**
     * Creates the command to mark a task.
     * @param idx index of task in list
     */
    public MarkCommand(String idx) {
        this.idx = idx;
        assert idx != null : "please fill in the index.";
    }

    /**
     * Marks the task as done.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) {
        int idxx = Integer.parseInt(idx) - 1;
        if (idxx < 0 || idxx >= tasks.getSize()) {
            String errMsg = "Cannot mark task " + (idxx + 1) + ": no such task.";
            ui.showMsg(ui.format(errMsg));
            return errMsg;
        }
        tasks.mark(idxx);
        String msg = "Nice! I've marked this task as done:\n  " + tasks.get(idxx).getDisplayString() + "\n";
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
