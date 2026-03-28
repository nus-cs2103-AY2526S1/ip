package sanbot.command;

import java.util.ArrayList;

import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Represents the command to mark tasks as completed by index.
 */
public class MarkDoneCommand implements Command {

    /**
     * Executes command that marks a task as done by its position number.
     * Format: "mark [task_number]"
     *
     * @param input the user input string containing the mark command and task number.
     * @param taskList the list of tasks containing the task to mark as done.
     * @throws ArrayIndexOutOfBoundsException if the input format is invalid or missing the task number.
     * @throws IndexOutOfBoundsException if the specified task index is out of bounds for the task list.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        String[] processedParts = input.split(" ");

        try {
            int num = Integer.parseInt(processedParts[1].trim()) - 1;
            taskList.get(num).markAsDone();

            return Ui.markDone(taskList.get(num));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.markDoneIndexError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.markDoneInvalidIndex();
        }
    }
}