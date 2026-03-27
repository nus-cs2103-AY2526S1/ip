package command;

import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Represents deleting a Task from the list.
 */
public class DeleteCommand implements Command {

    private String idx;

    /**
     * Creates the delete command for deleting a task from list
     * @param idx index of task in the list
     */
    public DeleteCommand(String idx) {
        this.idx = idx;
        assert idx != null : "please fill in the index.";
    }

    /**
     * Removes the specified task using its index.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) {
        int idxx = Integer.parseInt(idx) - 1;
        if (idxx < 0 || idxx >= tasks.getSize()) {
            String errMsg = "Cannot delete task " + (idxx + 1) + ": no such task.";
            ui.showMsg(ui.format(errMsg));
            return errMsg;
        }

        String msg = "Noted. I've removed this task:\n  " + tasks.get(idxx).getDisplayString() + "\n";
        ui.showMsg(ui.format(msg));
        tasks.delete(idxx);
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
