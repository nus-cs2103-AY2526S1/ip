package tinkerton.task;

import java.util.ArrayList;

public class StubTaskList extends TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int addCount = 0;

    @Override
    public void add(Task task) {
        tasks.add(task);
        addCount++;
    }

    @Override
    public Task get(int index) {
        return tasks.get(index);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    public int getAddCount() {
        return addCount;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getLastAddedTask() {
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks.get(tasks.size() - 1);
    }
}
