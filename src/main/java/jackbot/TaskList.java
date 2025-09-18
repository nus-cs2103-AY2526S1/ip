package jackbot;

import jackbot.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> asList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws JackbotException {
        int idx = index - 1;
        if (idx < 0 || idx >= tasks.size()) throw new JackbotException("Task not found");
        return tasks.remove(idx);
    }

    public Task get(int index) throws JackbotException {
        int idx = index - 1;
        if (idx < 0 || idx >= tasks.size()) throw new JackbotException("Task not found");
        return tasks.get(idx);
    }
}
