package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Tasks.Todo;
import LunarBot.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.add(new Todo(description, false));
        return ui.showMessage("Okay! I'll add this to your TODOs");
    }
}
