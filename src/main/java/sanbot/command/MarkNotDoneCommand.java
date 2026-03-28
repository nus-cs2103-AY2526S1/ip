package sanbot.command;

import java.util.ArrayList;

import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Represents a command to mark tasks as not completed by index.
 */
public class MarkNotDoneCommand implements Command {

    /**
     * Executes the command to mark a task as not done by its position number.
     * Format: "unmark [task_number]"
     *
     * @param input the user input string containing the unmark command and task number.
     * @param taskList the list of tasks containing the task to mark as not done.
     * @throws ArrayIndexOutOfBoundsException if the input format is invalid or missing the task number.
     * @throws IndexOutOfBoundsException if the specified task index is out of bounds for the task list.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        String[] processedParts = input.split(" ");

        try {
            int num = Integer.parseInt(processedParts[1].trim()) - 1;
            taskList.get(num).markAsNotDone();

            return Ui.markNotDone(taskList.get(num));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.markNotDoneIndexError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.markNotDoneInvalidIndex();
        }
    }
}
