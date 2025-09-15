package chirp.actions;

import java.util.Scanner;

import chirp.exceptions.ChirpException;
import chirp.io.Ui;
import chirp.tasks.TaskList;

/**
 * Represents action to mark a task as done or not done
 */
public class MarkAction extends Action {
    private boolean isDone;
    private int index;

    /**
     * Creates MarkAction from user input
     *
     * @param command Command used to determine whether to mark or unmark
     * @param input   User input to extract date filter
     * @throws ChirpException
     */
    public MarkAction(Command command, String input) {
        Scanner inputSc = new Scanner(input);
        inputSc.next();
        index = inputSc.nextInt() - 1;
        assert(command == Command.MARK || command == Command.UNMARK) : "Invalid command passed to MarkAction";
        isDone = command == Command.MARK;
    }

    /**
     * Updates whether a task is done
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws ChirpException {
        taskList.markTask(index, isDone);
        ui.printMessage("Modified task: " + taskList.getTask(index));
    }
}
