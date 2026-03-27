package command;

import amogus.AmogusException;
import amogus.FileStorage;
import amogus.UI;
import tasks.Deadlines;
import tasks.TaskList;

/**
 * This class creates a Deadlines object to be added
 * to the list of tasks.
 */
public class AddDeadlineCommand implements Command {

    private String desc;

    /**
     * Creates the command to add a deadline object
     * @param desc description of deadline
     */
    public AddDeadlineCommand(String desc) {
        this.desc = desc;
        assert desc != null : "no empty description";
    }

    /**
     * Creates the Tasks.Deadlines object to be added into the list of tasks.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     * @throws AmogusException insufficient information to create the task
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) throws AmogusException {
        String[] parts = desc.split("/by");
        String descr = parts[0].trim();
        String by = parts[1].trim();

        assert by != null : "deadline cannot be empty";

        Deadlines deadlines = new Deadlines(descr, by);
        tasks.add(deadlines);
        String msg = "Got it. I've added this task:\n  " + deadlines.getDisplayString() + "\nNow you have "
                + tasks.getSize() + " tasks in the list.\n";
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
