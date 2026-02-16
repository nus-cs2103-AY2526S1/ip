package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;
import bambam.task.Task;

/**
 * Represents the mark command which is a type of Command.
 */
public class MarkCommand extends Command {
    private Task task;
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {
        int index = taskNumber - 1;

        assert (index >= 0 && index < taskList.getTaskSize()) :
                "Task Number provided is not in the Task List:" + taskNumber;

        task = taskList.markTaskAsDone(index);

        storage.saveTasks(taskList);
    }

    @Override
    public String getString() {
        return "✅ Yay! I've marked this task as done:\n" +
                "    " + task.printTaskString() + "\n" +
                "Keep it up! 🌸💖\n";
    }
}
