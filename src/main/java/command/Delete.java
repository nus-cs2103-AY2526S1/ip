package command;

import java.util.List;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import task.Task;
import ui.Ui;

/**
 * Represents a {@code Delete} command, that deletes a {@link Task} to the task list.
 */
public class Delete implements Command {
    /**
     * Stores index of task to be deleted from storage
     */
    private final int index;

    /**
     * Initialises command object with corresponding index
     * @param index Index of task to be deleted
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Executes the command, by deleting the task in the specified index in the storage
     * @param storage   storage currently being used
     * @throws DukeException    Handles error during execution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        if (storage.isEmpty()) {
            throw new DukeException("Index does not exist! Attempted to mark " + this.index);
        } else {
            List<Task> t = storage.getTasks();
            Task gone = t.remove(this.index);
            storage.readToFile();
            Ui.display(new String[]{"Noted. I've removed this task:", gone.toString(),
                                    "Now you have " + t.size() + " tasks in the list."});
            return new String[]{"Noted. I've removed this task:", gone.toString(),
                                "Now you have " + t.size() + " tasks in the list."};
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Delete c) {
            return this.index == c.index;
        }
        return false;
    }
}
