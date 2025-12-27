package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to mark task as done
 */
public class MarkCommand implements Command {
    private static final String PREFIX = "mark ";
    private static final int PREFIX_LENGTH = PREFIX.length();

    /**
     * Marks task as done from TaskList based on index displayed on UI
     * Rewrites data file with updated TaskList and calls UI
     *
     * @throws UnknownInputException if task does not exist or no index is given
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("'mark' requires a number after");
        }

        String numberString = input.substring(PREFIX_LENGTH).trim();

        boolean isValidNumber = NumberValidator.isValidNumber(numberString);

        if (!isValidNumber) {
            throw new UnknownInputException("'mark' requires a number after");
        }

        int index = Integer.parseInt(numberString);

        if (index < 1 || index > tasks.size()) {
            throw new UnknownInputException("you can't mark a task that doesn't exist");
        }

        Task curr = tasks.get(index - 1);
        curr.setDone(true);
        storage.rewriteStorage(tasks);
        assert curr.getStatusIcon().equals("X") : "Task not marked done";

        return ui.showMark(curr);
    }
}
