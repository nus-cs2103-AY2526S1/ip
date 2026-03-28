package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * This class checks the user input, ensuring that the task number is a
 * positive integer.
 */
public class MarkCommand extends Command {

    private final int taskIndex;

    /**
     * Construct a MarkCommand object based on user input.
     * The user input must be in the format: "mark task number".
     *
     * @param input user input containing task number.
     * @throws InvalidCommandFormatException if the input format is invalid or task number is not a positive integer.
     */
    public MarkCommand(String input) throws InvalidCommandFormatException {
        this.taskIndex = indexParser(input);
    }

    /**
     * To check the valid task index from user input.
     * @param input user input string.
     * @return task index as integer .
     * @throws InvalidCommandFormatException if input is empty or invalid number.
     */
    private int indexParser(String input) throws InvalidCommandFormatException {
        String[] entries = input.split(" ", 2);
        assert entries.length > 0 : "Input should not be empty";
        assert !entries[0].isEmpty() : "Input should start with 'mark' command";
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
        this.response = taskList.mark(this.taskIndex);
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
