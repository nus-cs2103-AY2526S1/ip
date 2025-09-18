package chiikawa.command;

import java.util.ArrayList;

import chiikawa.ChiikawaException;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.Task;
import chiikawa.task.Task.Priority;

/**
 * Class to show tasks
 */
public class FilterCommand extends Command {
    private String priorityStr;

    /**
     * Constructor that takes in a string representation of a keyword and stores it.
     *
     * @param priority
     */
    public FilterCommand(String priority) {
        this.priorityStr = priority.trim(); // Copilot suggested!
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException {
        String output = "";
        ArrayList<Task> taskList = tasks.getTaskList();

        if (this.priorityStr.isEmpty()) {
            throw new ChiikawaException("filter by wha?");
        }

        try {
            String priorityStr = this.priorityStr.strip().toUpperCase();
            Priority priority = Priority.valueOf(priorityStr);

            for (int i = 0; i < taskList.size(); i++) { // Copilot spotted my mistake of not using taskList.
                if (taskList.get(i).getPriority() != priority) {
                    taskList.get(i).hideTask();
                }
            }

            output += ui.showFilter(this.priorityStr) + "\n" + ui.showList(tasks);
        } catch (IllegalArgumentException e) {
            throw new ChiikawaException("can only feelter by HIGH or LOW!! HIGH!! LOW!!");
        }

        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).unhideTask();
        }

        return output;
    }
}
