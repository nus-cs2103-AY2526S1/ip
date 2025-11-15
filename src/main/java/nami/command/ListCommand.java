package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;

public class ListCommand extends Command {
    /**
     * Executes the task of listing all the task
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("__________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1));
            sb.append(". ");
            sb.append(tasks.get(i).getList());
            sb.append("\n");
        }
        sb.append("__________________________________");
        return sb.toString();
    }
}
