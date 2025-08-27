package beebong.task;

import java.util.ArrayList;
import java.util.List;

import beebong.storage.Storage;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // For loading from file
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int length() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAs(int taskNum, boolean isComplete) {
        if (isComplete) {
            this.tasks.get(taskNum).markCompleted();
        } else {
            this.tasks.get(taskNum).markIncomplete();
        }
    }

    public Task deleteTask(int taskNum) {
        return this.tasks.remove(taskNum);
    }

    public void writeTasksToFile(Storage storage) {
        storage.writeTasksToFile(tasks);
    }

    public TaskList findTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task t : tasks) {
            // Make both to lowerCase to allow case-insensitive matching
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                newTaskList.addTask(t);
            }
        }
        return newTaskList;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return output.toString();
    }
}
