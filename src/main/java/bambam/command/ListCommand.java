package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;

/**
 * Represents the list command which is a type of Command.
 */
public class ListCommand extends Command {
    private String taskListString;
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {
        taskListString = taskList.getTaskListString();
    }

    @Override
    public String getString() {
        return "📋 Here are the tasks in your list, all organized for you:\n" +
                taskListString;
    }
}
