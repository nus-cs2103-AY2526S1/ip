package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        String toPrint = ui.showMessage("Printing tasks!") + "\n";
        for (int i = 1; i < taskList.size() + 1; i++) {
            toPrint += ui.showMessage(i + ": " + taskList.get(i-1).print()) + "\n";
        }
        return toPrint;
    }
}
