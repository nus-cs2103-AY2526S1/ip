package command;

import java.util.List;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import task.Task;
import ui.Ui;

/**
 * Represents an Add command, that adds a {@link Task} to the task list.
 */
public class Add implements Command {
    /**
     * The task to be added
     */
    private final Task task;

    /**
     * Initialises command object with corresponding task
     * @param task Task to be added
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the command, by adding the appropriate task to the list in storage
     * @param storage   storage currently being used
     * @throws DukeException    Handles error during execution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        List<Task> t = storage.getTasks();
        t.add(this.task);
        storage.readToFile();
        Ui.display("Added ", this.task.toString(), "Now you have " + t.size() + " tasks in the list.");
        return new String[]{"Added ", this.task.toString(), "Now you have " + t.size() + " tasks in the list."};
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Add c) {
            return this.task.equals(c.task);
        }
        return false;
    }
}
