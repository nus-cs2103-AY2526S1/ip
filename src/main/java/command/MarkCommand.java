package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;
import tasks.Task;

/**
 * Command to mark a task in the tasklist as done.
 */
public class MarkCommand implements Command {
    private final int markIdx;

    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    /**
     * Factory method to construct a MarkCommand class from the user input
     * @param arguments A list of user-input strings
     * @return An instance of the MarkCommand object
     * @throws PepeException if an exception occurred while parsing user input or constructing MarkCommand class
     */
    public static MarkCommand fromInput(String[] arguments) throws PepeException {
        int markIdx;
        if (arguments.length != 1) {
            throw new PepeException("Invalid number of arguments");
        }
        try {
            markIdx = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            throw new PepeException("Invalid mark index " + arguments[0]);
        }
        return new MarkCommand(markIdx);
    }

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        if (tasks.size() <= markIdx || markIdx < 0) {
            throw new PepeException("Submitted task idx is out of bounds");
        }
        Task task = tasks.get(markIdx);
        task.setDone(true);
        storage.saveTasks(tasks);
        String message = "    Nice! I've marked this task as done:\n    %s\n".formatted(task);
        return new Pair<>(ui.formatMessage(message), true);
    }
}
