package commands;

import core.Event;
import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends Command {

    public EventCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the event command to add an event task to the task list.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts String array containing the parsed parts of the user input.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForInvalidInputEventTask = "I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!";
        if (parts.length == 1) {
            throw new TronaldDumpException(errorMessageForInvalidInputEventTask);
        }

        taskList.addTask(new Event(input.substring(6), false));
        Ui.showAddTask(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
