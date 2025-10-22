package robert.command;

import robert.exception.DuplicateTaskException;
import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;
import robert.task.Todo;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Command to add a Todo task.
 */
public class AddTodoCommand extends Command {
    private final Todo todo;

    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException {
        checkForDuplicate(tasks);
        tasks.add(todo);
        storage.save(tasks);
        return formatAddedMessage(tasks);
    }

    private void checkForDuplicate(TaskList tasks) throws DuplicateTaskException {
        if (tasks.isDuplicate(todo)) {
            Task existingTask = tasks.findDuplicate(todo);
            throw new DuplicateTaskException(existingTask.toString());
        }
    }

    private String formatAddedMessage(TaskList tasks) {
        return "Got it. I've added this task:\n  " + todo 
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }
}