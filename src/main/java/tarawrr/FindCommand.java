package tarawrr;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        assert keyword != null : "keyword to find should not be null";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTasks();
        TaskList list = new TaskList();

        for (Task t : taskList) {
            if (t.getDescription().contains(keyword) || t.toString().contains(keyword)) {
                list.addToTaskList(t);
            }
        }

        if (list.numberOfTasks() == 0) {
            return ui.showError("No matching tasks found :(");
        }

        return ui.showFindTask(list);
    }


}
