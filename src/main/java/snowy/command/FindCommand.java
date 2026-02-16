package snowy.command;

import java.util.ArrayList;

import snowy.ui.Ui;
import snowy.TaskList;
import snowy.Storage;
import snowy.task.Task;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds tasks that contain the keyword from user.
     * @param tasks
     * @param storage
     * @return A string that is either the string format of the found tasks, or no tasks found
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();
        int count = tasks.getSize();
        for (int i = 0; i < count; i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(keyWord.toLowerCase())) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            return "No tasks found with keyword: " + keyWord + ":(";
        } else {
            TaskList output = new TaskList(result);
            return "I found the following:\n" + output.toString();
        }
    }
}
