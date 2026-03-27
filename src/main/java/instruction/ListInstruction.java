package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to display all tasks in the task list.
 * This instruction returns the current state of all tasks.
 */
public class ListInstruction extends Instruction {
    /**
     * Executes the list instruction by returning all tasks in the task list.
     *
     * @param tasks   the task list to be displayed
     * @param ui      the user interface for generating the task list message
     * @param storage the storage system (unused in this instruction)
     * @return formatted string of all tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTaskList(tasks.getAllTasks());
    }
}
