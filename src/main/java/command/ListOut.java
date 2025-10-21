package command;

import java.util.List;
import java.util.stream.IntStream;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import task.Task;
import ui.Ui;

/**
 * Represents a List command, that lists all tasks
 */
public class ListOut implements Command {
    /**
     * Executes the command, by listing all tasks in storage
     * @param storage   storage currently being used
     * @throws DukeException    Handles error during execution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        List<Task> t = storage.getTasks();
        String[] output = new String[t.size() + 1];
        IntStream.range(0, t.size()).forEach(i -> output[i + 1] = ((i + 1) + ". " + t.get(i)));
        output[0] = "Here are your tasks! There are a total of " + t.size() + " tasks.";
        Ui.display(output);
        return output;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListOut;
    }
}
