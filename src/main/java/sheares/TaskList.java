package sheares;

import java.util.ArrayList;

import sheares.task.Task;

/**
 * helper data strcuture to store Tasks for chatbot
 */
public class TaskList {
    private final ArrayList<Task> ls;

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public TaskList() {
        this.ls = new ArrayList<>();
    }

    public int size() {
        return this.ls.size();
    }

    public void add(Task task) {
        ls.add(task);
    }

    public void delete(int index) {
        ls.remove(index);
    }

    public Task get(int index) {
        return ls.get(index);
    }
}
