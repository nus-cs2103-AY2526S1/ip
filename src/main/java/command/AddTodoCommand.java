package command;

import task.TaskList;
import task.TodoTask;
import ui.Ui;

/**
 * Command implementation for adding todo tasks.
 *
 * Note: GitHub Copilot helped with implementing consistent command patterns
 * and suggesting input validation approaches.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        TodoTask todoTask = new TodoTask(description);
        tasks.add(todoTask);
        return "This task has been successfully added:\n" + todoTask;
    }
}