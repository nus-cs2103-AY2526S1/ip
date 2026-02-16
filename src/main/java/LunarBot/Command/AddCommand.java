package LunarBot.Command;

import LunarBot.Tasks.Task;
import LunarBot.TaskList;
import LunarBot.Ui;

public class AddCommand extends Command {
    private final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.add(new Task(description, false));
        return ui.showMessage("added: " + description);
    }
}
