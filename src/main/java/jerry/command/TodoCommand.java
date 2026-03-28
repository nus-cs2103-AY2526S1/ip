package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.task.ToDo;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * This class checks user input to ensure no empty description.
 * The task is added to the task list, saved to storage and a confirmation
 * message is displayed to the user.
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Parses user input to separate 'todo' command keyword and task description.
     * Exceptions is thrown when no task description is provided in the user input.
     *
     * @param desc user input to be parsed.
     * @throws InvalidCommandFormatException if user input is invalid or empty task description.
     */
    public TodoCommand(String desc) throws InvalidCommandFormatException {
        String trimmed = desc.trim();
        if (trimmed.toLowerCase().startsWith("todo")) {
            trimmed = trimmed.substring(4).trim();
            assert !trimmed.isEmpty() : "Description must not be empty";
        }
        if (trimmed.isEmpty()) {
            throw new InvalidCommandFormatException("You forgot to describe what your todo is...");
        }
        this.desc = trimmed;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        ToDo todo = new ToDo(desc);
        this.response = taskList.addTask(todo);
        taskList.saveTasks(storage);
        ui.displayOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }
}
