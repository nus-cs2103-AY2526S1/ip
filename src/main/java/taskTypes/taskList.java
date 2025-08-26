package taskTypes;

import exceptions.TaskLimitException;

import java.util.ArrayList;
import java.util.List;

public class taskList {
    protected List<Task> listOfTasks;

    //default taskList to intiate
    public taskList() {
        listOfTasks = new ArrayList<>();
    }

    //taskList that returns stored tasks
    public taskList(List<Task> loadTasks) {
        this.listOfTasks = loadTasks;
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

    public void deleteFromList(Task task) {
        listOfTasks.remove(task);
    }

    public int getTaskCount() {
        return listOfTasks.size();
    }
}
