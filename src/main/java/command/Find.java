package command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import task.Task;
import ui.Ui;

/**
 * Matches all {@link Task} in task list that have given string
 * in description.
 */
public class Find implements Command {
    /**
     * Substring to be found in description
     */
    private final String text;

    /**
     * Initialises command object with substring
     * @param text Substring to be found
     */
    public Find(String text) {
        this.text = text;
    }

    /**
     * Matches all tasks in storage task list that have a
     * description containing given substring.
     * @param storage   storage under consideration
     * @throws DukeException    Handles any error during execution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        List<Task> tasks = storage.getTasks();
        List<Task> found = new ArrayList<>();

        for (Task t : tasks) {
            if (t.containsString(this.text)) {
                found.add(t);
            }
        }

        String[] output = new String[found.size() + 1];
        IntStream.range(0, found.size()).forEach(i -> output[i + 1] = ((i + 1) + ". " + found.get(i)));
        output[0] = "Here are your tasks! Found " + found.size() + " matching tasks.";
        Ui.display(output);
        return output;
    }
}
