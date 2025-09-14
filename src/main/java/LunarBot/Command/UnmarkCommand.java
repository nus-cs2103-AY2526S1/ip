package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        String toPrint = ui.showMessage("Okay, I'll unmark that!") + "\n";
        taskList.get(this.index).setCompleted(false);
        toPrint += ui.showMessage(taskList.get(this.index).print());
        return toPrint;
    }
}
