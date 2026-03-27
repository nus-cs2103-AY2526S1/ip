package instruction;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * Represents an instruction to add a task to the task list.
 * This instruction handles the execution of adding various types of tasks
 * (Todo, Deadline, Event) to the application.
 */
public class AddInstruction extends Instruction {
    private Task taskToAdd;

    /**
     * Constructs an AddInstruction with the specified task to be added.
     *
     * @param task the task to be added to the task list
     */
    public AddInstruction(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the add instruction by adding the task to the task list,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   the task list to which the task will be added
     * @param ui      the user interface for generating messages
     * @param storage the storage system for persisting task data
     * @return confirmation message about the added task
     * @throws ShrekException if an error occurs during task addition or storage operations
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        if (tasks.hasDuplicate(taskToAdd)) {
            throw new ShrekException("Shrek already has this onion! (Duplicate task detected)\n"
                    + "Task: " + taskToAdd.getDescription());
        }
        tasks.add(taskToAdd);
        storage.save(tasks.getAllTasks());
        return ui.printAddedTask(taskToAdd, tasks.size());
    }
}
