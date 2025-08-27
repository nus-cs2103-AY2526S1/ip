package tasks;

import exception.RainyException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws RainyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RainyException("oh no!!! that task number doesn’t exist :c");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws RainyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RainyException("oh no!!! that task number doesn’t exist :c");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) throws RainyException {
        getTask(index).markAsDone();
    }

    public void unmarkTask(int index) throws RainyException {
        getTask(index).unmark();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void printList() {
        System.out.println("here's your list so far:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
