package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int i) {
        this.index = i-1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.delete(this.index);
        return "Okay, I'll delete this one from your list!\n" + 
            taskList.get(this.index).print();
    }
}
