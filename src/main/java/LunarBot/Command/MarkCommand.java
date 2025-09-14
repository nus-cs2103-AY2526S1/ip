package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        String toPrint = ui.showMessage("Okay, I'll mark that one off your list!") + "\n";
        taskList.get(this.index).setCompleted(true);
        toPrint += ui.showMessage(taskList.get(this.index).print());
        return toPrint;
    }
}
