package sanbot.command;

import java.util.ArrayList;

import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Command to find tasks using a keyword.
 */
public class FindTasksCommand implements Command {

    /**
     * Finds related tasks using input keyword.
     * Format: "find [keyword]"
     *
     * @param input the user input string containing the find command and keyword
     * @param taskList the list of tasks containing the task to mark as not done
     * @throws ArrayIndexOutOfBoundsException if the input format is invalid or missing the task number
     * @throws IndexOutOfBoundsException if the specified task index is out of bounds for the task list
     */
    public String execute(String input, ArrayList<Task> taskList) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        try {
            String key = input.substring(5).trim();
            for (Task task : taskList) {
                String[] split = task.toString().split(" ");
                for (String word : split) {
                    if (word.toLowerCase().equals(key)) {
                        foundTasks.add(task);
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.missingFindTaskInput();
        }

        if (foundTasks.isEmpty()) {
            return Ui.noTasksFound();
        }
        return Ui.printFoundTasks(foundTasks);
    }
}
