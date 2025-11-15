package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class TagCommand extends Command{
    private int idx;
    private String tag;
    public TagCommand(int idx, String tag) {
        this.idx = idx;
        this.tag = tag;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(idx - 1).tag(tag);

    }
}
