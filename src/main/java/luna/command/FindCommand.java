package luna.command;

import luna.storage.Storage;
import luna.task.TaskList;

public class FindCommand extends IntermediateCommand {
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        return "Here are the matching tasks in your list:\n" + taskList.find(search);
    }
}
