package robert.command;

import robert.exception.DuplicateTaskException;
import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Event;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Command to add an Event task.
 */
public class AddEventCommand extends Command {
    private final Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException {
        checkForDuplicate(tasks);
        tasks.add(event);
        storage.save(tasks);
        return formatAddedMessage(tasks);
    }

    private void checkForDuplicate(TaskList tasks) throws DuplicateTaskException {
        if (tasks.isDuplicate(event)) {
            Task existingTask = tasks.findDuplicate(event);
            throw new DuplicateTaskException(existingTask.toString());
        }
    }

    private String formatAddedMessage(TaskList tasks) {
        return "Got it. I've added this task:\n  " + event 
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }
}