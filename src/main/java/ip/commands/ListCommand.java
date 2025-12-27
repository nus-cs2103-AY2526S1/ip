package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to display TaskList
 */
public class ListCommand implements Command {
    private static final String KEYWORD = "list";

    /**
     * Displays the current TaskList by iterating through the TaskList and printing each to output
     *
     * @throws UnknownInputException if other words are included or empty TaskList
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {

        if (!input.equals(KEYWORD)) {
            throw new UnknownInputException("Just enter 'list' by itself");
        }

        if (tasks.isEmpty()) {
            throw new UnknownInputException("your list is empty");
        }

        assert !tasks.isEmpty() : "TaskList is empty";

        return ui.showListCommand(tasks);
    }
}
