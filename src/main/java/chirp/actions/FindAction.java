package chirp.actions;

import chirp.exceptions.InvalidAttributeException;
import chirp.io.Parser;
import chirp.io.Ui;
import chirp.tasks.TaskList;

/**
 * Represents action to filter tasks
 */
public class FindAction extends Action {
    private String filter;

    /**
     * Creates FindAction from user input
     *
     * @param input User input to extract string filter
     * @throws InvalidAttributeException
     */
    public FindAction(String input) throws InvalidAttributeException {
        filter = Parser.extractAttribute(input, Command.FIND.getKeyword());
        if (filter.isEmpty()) {
            throw new InvalidAttributeException("", "find", "Empty find filter");
        }
    }

    /**
     * Finds tasks that contain filter string
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printMessage("Here are the matching tasks in your list:", taskList.displayStr(filter));
    }
}
