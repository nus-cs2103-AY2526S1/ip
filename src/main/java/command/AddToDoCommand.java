package command;

import amogus.AmogusException;
import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;
import tasks.ToDo;

/**
 * This class creates the ToDo object to be added
 * to the list of tasks.
 */
public class AddToDoCommand implements Command {

    private String desc;

    /**
     * Creates the command to add a todo object
     * @param desc description of todo
     */
    public AddToDoCommand(String desc) throws AmogusException {
        this.desc = desc;
        assert desc != null : "no empty description";
    }

    /**
     * Creates the Tasks.ToDo object to be added into the list of tasks.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     * @throws AmogusException insufficient information to create the task
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) throws AmogusException {
        ToDo todo = new ToDo(desc);
        tasks.add(todo);

        String msg = "Got it. I've added this task:\n  " + todo.getDisplayString() + "\nNow you have "
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
