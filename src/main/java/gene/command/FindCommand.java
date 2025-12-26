package gene.command;

import gene.tasks.Task;
import gene.TaskList;
import gene.Ui;

import java.util.ArrayList;

import static gene.TaskList.getStringFromTasks;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    public String execute(TaskList tasksList) {
        assert tasksList != null;
        ArrayList<Task> tasksListKeyword = tasksList.findKeyword(this.keyword);

        class Helper {
            String formatTaskList(ArrayList<Task> tasks) {
                if (tasksListKeyword.isEmpty()) {
                    return Ui.SPACING + "No records found.";
                }
                StringBuilder res = new StringBuilder();
                res.append(Ui.SPACING).append("Here are the matching" +
                        " task in your list:\n");
                return getStringFromTasks(res, tasks);
            }
        }

        return new Helper().formatTaskList(tasksListKeyword);
    }
}
