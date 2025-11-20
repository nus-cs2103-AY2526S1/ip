package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import tasks.ToDos;
import ui.Ui;

/**
 * The AddTodoCommand class represents a command to add a new To-Do task to the task list.
 * It takes a description of the task and adds it to the provided TaskList.
 * This class extends the Command class.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        // store description; validation happens in execute()
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand. This method creates a new To-Do task using the provided
     * description, adds it to the TaskList, and saves the updated TaskList to storage.
     *
     * @param taskList The task list where the To-Do task will be added.
     * @param ui The UI object to interact with the user (not used in this method).
     * @param storage The storage object used to save the updated TaskList.
     * @throws JackException if the description is null or empty.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (description == null || description.trim().isEmpty()) {
            throw new JackException("The description of a todo cannot be empty.");
        }

        Task task = new ToDos(description);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.showAdd(task, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
