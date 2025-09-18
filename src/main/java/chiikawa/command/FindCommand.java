package chiikawa.command;

import java.util.ArrayList;

import chiikawa.ChiikawaException;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.Task;

/**
 * Class for filtering tasks with a certain keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor that takes in a string representation of a keyword and stores it.
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException {
        String output = "";

        if (this.keyword.isEmpty()) {
            throw new ChiikawaException("find wat? hah?");
        }

        this.keyword = this.keyword.strip();
        ArrayList<Task> taskList = tasks.getTaskList();

        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).getName().contains(this.keyword)) {
                taskList.get(i).hideTask();
            }
        }

        output += ui.showFind(this.keyword) + "\n" + ui.showList(tasks);

        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).unhideTask();
        }

        return output;
    }
}
