package robert.command;

import robert.exception.DuplicateTaskException;
import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Deadline;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException {
        checkForDuplicate(tasks);
        tasks.add(deadline);
        storage.save(tasks);
        return formatAddedMessage(tasks);
    }

    private void checkForDuplicate(TaskList tasks) throws DuplicateTaskException {
        if (tasks.isDuplicate(deadline)) {
            Task existingTask = tasks.findDuplicate(deadline);
            throw new DuplicateTaskException(existingTask.toString());
        }
    }

    private String formatAddedMessage(TaskList tasks) {
        return "Got it. I've added this task:\n  " + deadline 
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }
}