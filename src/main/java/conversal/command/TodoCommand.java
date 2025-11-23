package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.task.Todo;
import conversal.ui.Ui;

/**
 * Represents a command to create and add a To-do task
 * to the task list.
 *
 * The TodoCommand is created when the user input starts with "todo "
 *
 * It extracts the description from the input,
 * creates a new To-do task based description in inout,
 * adds it to the TaskList,
 * saves the updated list to Storage,
 * updates the user through Ui
 *
 */
public class TodoCommand implements Command {

    // Fields
    private String input;

    /**
     * Creates a TodoCommand using the user input.
     *
     * @param input the user input containing the description of the to-do
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to create and add a new To-do task.
     *
     * If the description is missing, a ConversalException is thrown.
     * Else, the new task is added to the TaskList,
     * the list is saved via Storage}, and a confirmation message is displayed
     * through Ui
     *
     * @param tasks   the task list to add the new to-do into
     * @param storage the storage of tasks
     * @param ui      the UI to show the confirmation message
     * @throws ConversalException if the to-do description is missing or invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionTodo());
        }
        String description = input.substring(5);
        Task t = new Todo(description);
        tasks.addTask(t);
        storage.save(tasks.getList());
        ui.addMessage(t, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
