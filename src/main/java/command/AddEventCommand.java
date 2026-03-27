package command;

import amogus.AmogusException;
import amogus.FileStorage;
import amogus.UI;
import tasks.Event;
import tasks.TaskList;

/**
 * This class creates a Event object to be added
 * to the list of tasks.
 */
public class AddEventCommand implements Command {

    private String desc;

    /**
     * Creates the command to add an Event object
     * @param desc description of event
     */
    public AddEventCommand(String desc) throws AmogusException {
        this.desc = desc;
        assert desc != null : "no empty description";
    }

    /**
     * Creates the Tasks.Event object to be added into the list of tasks.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     * @throws AmogusException insufficient information to create the task
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) throws AmogusException {
        String[] parts = desc.split("/from|/to");
        String descr = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();

        assert start != null : "start date cannot be empty";
        assert end != null : "end date cannot be empty";

        Event event = new Event(descr, start, end);
        tasks.add(event);
        String msg = "Got it. I've added this task:\n  " + event.getDisplayString() + "\nNow you have "
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
