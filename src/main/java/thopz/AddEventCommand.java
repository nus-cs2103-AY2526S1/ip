package thopz;

import java.io.IOException;


/**
 * Adding a new event task
 */
public class AddEventCommand extends Command {
    private String desc;
    private String start;
    private String end;

    /**
     * Constructs a command to add event with the specified description and due date.
     *
     * @param desc  the description of the task
     * @param start start time/date
     * @param end end time/date
     *
     */

    public AddEventCommand(String desc, String start, String end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the add-event command by adding an event task to the task list.
     *
     * @param list   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    @Override
    public String perform(TaskList list, Storage storage, Ui ui) {
        Events task = new Events(desc, start, end);
        list.addTask(task);
        try {
            storage.save(list.getAllTasks());
        } catch (IOException e) {
            return("Cannot save changes");
        }
        return ui.showAddition(task, list.size());
    }
}
