package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to add a new todo task.
 */
public class AddTodoCommand implements Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() { // <-- add this getter
        return description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addTodo(description);
        storage.saveTasks(tasks.getTasks());

        String message = "Got it. I've added this task:\n"
                + tasks.getTask(tasks.size() - 1).toString()
                + "\nNow you have " + tasks.size() + " tasks in the list\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
