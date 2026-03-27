package command;

import amogus.AmogusException;
import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Represents tagging a Task.
 */
public class TagCommand implements Command {

    private String desc;

    /**
     * Creates the command to execute tagging a task.
     * @param desc desc of tag
     */
    public TagCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Tags the task with the given tag information.
     * @param tasks list of tasks
     * @param ui ui
     * @param f list of tasks txt file
     * @return message of completed tag
     * @throws AmogusException empty tag
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) throws AmogusException {
        String[] parts = desc.split(" ", 2);
        int idx = Integer.parseInt(parts[0]) - 1;
        String tag = parts[1];
        assert tag != null : "Tag cannot be empty";
        try {
            tasks.tag(idx, tag);
        } catch (AmogusException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        String msg = "Done! I've managed to tag your task: " + tasks.get(idx).getDisplayString() + "\n";
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
