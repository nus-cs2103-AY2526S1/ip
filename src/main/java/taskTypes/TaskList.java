package taskTypes;

import exceptions.TaskLimitException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> listOfTasks;

    //default taskList to intiate
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    //taskList that returns stored tasks
    public TaskList(List<Task> loadTasks) {
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

    public List<Task> getTasksAtDate(LocalDate date) {
        List<Task> result = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getDate().equals(date)) {
                    result.add(task);
                }
            } else if (task instanceof Event event) {
                if ((event.getFrom().equals(date) || date.isAfter(event.getFrom())) &&
                        (event.getTo().equals(date) || date.isBefore(event.getTo()))) {
                    result.add(task);
                }
            }
        }
        return result;

    }
}
