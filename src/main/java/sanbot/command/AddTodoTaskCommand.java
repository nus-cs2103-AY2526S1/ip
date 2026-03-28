package sanbot.command;

import java.util.ArrayList;

import sanbot.task.Task;
import sanbot.task.TodoTask;
import sanbot.ui.Ui;

/**
 * Represents a command to add simple todo tasks to the task list.
 */
public class AddTodoTaskCommand implements Command{

    /**
     * Creates a todo task from input description.
     * Format: "todo [description]"
     *
     * @param input the user input string containing the "todo" command and task description.
     * @param taskList the list of tasks to add the new todo task to.
     * @return the message to be displayed to user upon execution of command.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        try {
            Task todoTask = new TodoTask(input.substring(5));
            taskList.add(todoTask);

            return Ui.todo(todoTask, taskList.size());
        } catch (Exception e) {
            return Ui.todoError();
        }
    }
}