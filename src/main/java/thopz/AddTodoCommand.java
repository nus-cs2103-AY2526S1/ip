package thopz;

import java.io.IOException;

/**
 * Adding a new todo task
 */

public class AddTodoCommand extends Command {

    private String desc;

    /**
     * Constructs a command to add todo with the specified description
     *
     * @param desc  the description of the task
     *
     */

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the add-todo command by adding an event task to the task list.
     *
     * @param list   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    @Override
    public String perform(TaskList list, Storage storage, Ui ui) {
        Todo task = new Todo(desc);
        list.addTask(task);
        try {
            storage.save(list.getAllTasks());
        } catch (IOException e) {
            return "Cannot save changes";
        }
        return ui.showAddition(task, list.size()); // return string instead of void
    }

}
