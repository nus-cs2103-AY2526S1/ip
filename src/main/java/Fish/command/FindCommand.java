package fish.command;

import fish.FishException;
import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Stands for a command that looks for a command by description.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FishException {
        TaskList t = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                t.add(tasks.get(i));
            }
        }

        if (t.size() == 0) {
            ui.showError("No matching tasks found for keyword: " + keyword);
        } else {
            new ListCommand().execute(t, ui, storage);
        }
    }
}
