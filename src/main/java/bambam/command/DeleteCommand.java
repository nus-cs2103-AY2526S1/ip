package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;
import bambam.task.Task;

/**
 * Represents the delete command which is a type of Command.
 */
public class DeleteCommand extends Command {
    private Task task;
    private int taskListSize;
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {
        int index = taskNumber - 1;
        task = taskList.getTask(index);
        taskList.deleteTaskFromList(index);

        taskListSize = taskList.getTaskSize();

        storage.saveTasks(taskList);
    }

    @Override
    public String getString() {
        return "🗑️ Oops! I've removed this task:\n" +
                "    " + task.printTaskString() + "\n" +
                "Now you have " + taskListSize + " tasks in the list. \uD83D\uDC96\n";
    }
}
