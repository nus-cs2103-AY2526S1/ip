package meo;

import java.util.ArrayList;
import java.util.List;

import meo.data.TextList;
import meo.task.Task;

/**
 * Finds task in task list.
 */

public class TaskFinder {
    private TextList taskList = new TextList();

    public TaskFinder(TextList taskList) {
        this.taskList = taskList;
    }

    /**
     * Finds task in task list by keyword input by user.
     * 
     * @param keywords Keywords given by user.
     * @return TextList object including all correct tasks.
     */
    public TextList find(String[] keywords) {
        List<Task> filteredTaskList = taskList.getAllTask().stream().filter(
                task -> containAnyKeyword(task.getDescription(), keywords))
                .toList();
        ArrayList<Task> newTaskList = new ArrayList<Task>(filteredTaskList);
        return new TextList(newTaskList);
    }

    private boolean containAnyKeyword(String description, String[] keywords) {
        for (String keyword : keywords) {
            if (description.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
