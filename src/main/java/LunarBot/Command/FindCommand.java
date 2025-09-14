package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Tasks.Task;
import LunarBot.Ui;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        String toPrint = "";
        for (Task task: taskList.all()) {
            if (task.getName().toLowerCase().contains(keyWord)) {
                toPrint += ui.showMessage(task.print()) + "\n";
            }
        }
        return toPrint;
    }
}
