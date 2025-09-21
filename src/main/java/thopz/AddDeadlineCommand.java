package thopz;

import java.io.IOException;


/**
 * Adding a new deadline task
 */

public class AddDeadlineCommand extends Command {
    private String desc;
    private String due;

    /**
     * Constructs a command to add deadline with the specified description and due date.
     *
     * @param desc  the description of the task
     * @param due the string representation of the due date/time
     *
     */

    public AddDeadlineCommand(String desc, String due) {
        this.desc = desc;
        this.due = due;
    }

    /**
     * Executes the add-deadline command by adding an event task to the task list.
     *
     * @param list   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */
    @Override
    public String perform(TaskList list, Storage storage, Ui ui) {
        Deadline task = new Deadline(desc, due);
        list.addTask(task);
        try {
            storage.save(list.getAllTasks());
        } catch (IOException e) {
            return "Cannot save changes";
        }
        return ui.showAddition(task, list.size());

    }
}
