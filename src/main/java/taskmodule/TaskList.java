package taskmodule;

import java.util.ArrayList;
import java.util.List;


public class TaskList {
    private final List<Task> taskStore;
    private int taskCount;

    public TaskList() {
        this.taskStore = new ArrayList<>();
        this.taskCount = 0;
    }

    public TaskList(List<Task> taskStore) {
        this.taskStore = taskStore;
        this.taskCount = taskStore.size();
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public Task getTask(int index) {
        assert index >= 0 && index < this.taskCount : "Task index should be within valid range.";

        if (index < 0 || index >= this.taskCount) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return this.taskStore.get(index);
    }

    public void addTask(Task task) {
        this.taskStore.add(task);
        this.taskCount++;
    }

    public Task deleteTask(int index) {
        assert index >= 0 && index < this.taskCount : "Task index should be within valid range.";

        if (index < 0 || index >= this.taskCount) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        this.taskCount--;
        return this.taskStore.remove(index);
    }

    public String toString() {
        if (this.taskCount == 0) {
            return "You have no tasks.";
        }
        return String.format("Now you have %d tasks in the list.", this.taskCount);
    }
}
