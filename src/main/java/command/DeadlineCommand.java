package command;

import exceptions.DeadlineMissingByException;
import exceptions.DeadlineMissingDescriptionException;
import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;
import task.Deadline;
import task.Task;

/**
 * Command to save task as deadline.
 */
public class DeadlineCommand extends Command {
    private final String arg;

    public DeadlineCommand(String arg) {
        this.arg = arg.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert arg != null : "Input cannot be null";

        String[] parts = arg.split("/by", 2);
        if (parts[0].isBlank()) {
            throw new DeadlineMissingDescriptionException();
        }

        if (parts.length < 2) {
            throw new DeadlineMissingByException();
        }

        Task task = new Deadline(parts[0], parts[1], false);
        taskList.add(task, storage);
        System.out.println(task.getAddMessage(taskList.size()));
    }
}
