package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * It parses the user input to extract the task number to be deleted and validates whether it is the correct format.
     * An exception will be thrown if the input is in invalid format or has invalid task number.
     *
     * @param input the user input in the expected format: "delete (task number)".
     * @throws InvalidCommandFormatException if the input is invalid or in an incorrect format.
     */
    public DeleteCommand(String input) throws InvalidCommandFormatException {
        this.taskIndex = indexParser(input);
    }

    /**
     * To check the valid task index from user input.
     * @param input user input string.
     * @return task index as integer.
     * @throws InvalidCommandFormatException if input is empty or invalid number.
     */
    private int indexParser(String input) throws InvalidCommandFormatException {
        String[] entries = input.split(" ", 2);
        assert entries.length > 0 : "Input should not be empty";
        assert !entries[0].isEmpty() : "Input should start with 'delete' command";
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
        try {
            int index = Integer.parseInt(entries[1]);
            if (index <= 0) {
                throw new InvalidCommandFormatException("Task number must be greater than 0");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        this.response = taskList.deleteTask(this.taskIndex);
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
