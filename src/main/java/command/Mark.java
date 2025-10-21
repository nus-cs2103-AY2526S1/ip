package command;

import java.util.List;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import task.Task;
import ui.Ui;

/**
 * Represents a {@code Mark} command, that marks or unmarks a {@link Task} to the task list.
 */
public class Mark implements Command {
    /**
     * Stores index of task to be marked/ unmarked
     */
    private final int index;
    /**
     * Stores boolean of whether task is completed
     */
    private final boolean mark;

    /**
     * Initialises command object with corresponding index and boolean
     * @param index Index of task being considered
     * @param mark  Boolean of whether task is completed
     */
    public Mark(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * Executes the command, by marking the task in the specified index in the storage
     * @param storage   storage currently being used
     * @throws DukeException    Handles error during execvution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        if (storage.isEmpty()) {
            throw new DukeException("Index does not exist! Attempted to mark " + this.index);
        } else {
            List<Task> t = storage.getTasks();
            t.get(this.index).setCompleted(this.mark);
            storage.readToFile();
            Ui.display(mark
                    ? new String[]{"Nice! I've marked this task as done:", t.get(this.index).toString()}
                    : new String[]{"Ok! I've marked this task as not done yet:", t.get(this.index).toString()});
            return mark
                    ? new String[]{"Nice! I've marked this task as done:", t.get(this.index).toString()}
                    : new String[]{"Ok! I've marked this task as not done yet:", t.get(this.index).toString()};
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Mark c) {
            return this.index == c.index && this.mark == c.mark;
        }
        return false;
    }
}
