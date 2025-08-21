package taskTypes;

import exceptions.TaskLimitException;

import java.util.ArrayList;
import java.util.List;

public class taskList {
    protected List<Task> listOfTasks;

    public taskList() {
        listOfTasks = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return listOfTasks;
    }

    public void addToList(Task task) throws TaskLimitException {
        if (listOfTasks.size() >= 99) {
            throw new TaskLimitException();
        } else {
            listOfTasks.add(task);
        }
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getTaskCount() {
        return listOfTasks.size();
    }
}
