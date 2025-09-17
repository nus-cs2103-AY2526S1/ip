package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;
import tasks.Task;

/**
 * Command to mark a task in the task list as done.
 */
public class UnmarkCommand implements Command {
    private final int unmarkIdx;

    public UnmarkCommand(int unmarkIdx) {
        this.unmarkIdx = unmarkIdx;
    }

    /**
     * Factory method to construct a UnmarkCommand class from the user input
     * @param arguments A list of user-input strings
     * @return An instance of the UnmarkCommand object
     * @throws PepeException if an exception occurred while parsing user input or constructing UnmarkCommand class
     */
    public static UnmarkCommand fromInput(String[] arguments) throws PepeException {
        int markIdx;
        if (arguments.length != 1) {
            throw new PepeException("Invalid number of arguments");
        }
        try {
            markIdx = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            throw new PepeException("Invalid mark index " + arguments[0]);
        }
        return new UnmarkCommand(markIdx);
    }

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        if (tasks.size() <= unmarkIdx || unmarkIdx < 0) {
            throw new PepeException("Submitted task idx is out of bounds");
        }
        Task task = tasks.get(unmarkIdx);
        task.setDone(false);
        storage.saveTasks(tasks);
        String message = "     OK, I've marked this task as not done yet:\n    %s\n".formatted(task);
        return new Pair<>(ui.formatMessage(message), true);
    }
}
