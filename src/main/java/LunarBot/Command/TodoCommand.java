package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Tasks.Todo;
import LunarBot.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay! I'll add this to your TODOs");
        taskList.add(new Todo(description, false));
    }
}
