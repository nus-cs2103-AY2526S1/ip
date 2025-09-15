package chirp.actions;

import java.util.Scanner;

import chirp.exceptions.ChirpException;
import chirp.io.Ui;
import chirp.tasks.Task;
import chirp.tasks.TaskList;

/**
 * Represents action to delete a task from the list
 */
public class DeleteAction extends Action {
    private int index;

    /**
     * Creates DeleteAction from user input
     *
     * @param input User input to extract task index
     */
    public DeleteAction(String input) {
        Scanner inputSc = new Scanner(input);
        inputSc.next();
        index = inputSc.nextInt() - 1;
    }

    /**
     * Deletes a task from the task list
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws ChirpException {
        Task task = taskList.deleteTask(index);
        ui.printMessage("Delete task: " + task, ui.taskListCount(taskList));
    }
}
