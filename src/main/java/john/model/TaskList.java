package john.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }
}
