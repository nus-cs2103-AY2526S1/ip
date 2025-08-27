package zell.task;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index - 1).setDone(true);
    }

    public void markTaskAsNotDone(int index) {
        this.tasks.get(index - 1).setDone(false);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public void removeTask(int index) {
        this.tasks.remove(index - 1);
    }

    public String listAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            stringBuilder.append(formatTask(i, currentTask));
        }

        return stringBuilder.toString();
    }

    public String listAllTasksContainingWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            String taskToString = currentTask.toString();

            if (taskToString.contains(word)) {
                stringBuilder.append(formatTask(count, currentTask));
                count++;
            }

        }

        return stringBuilder.toString();
    }

    public String formatTask(int num, Task task) {
        return String.format(" %d. %s\n", num + 1, task);
    }

    public List<String> getAllTasksInString() {
        List<String> tasks = new ArrayList<>();

        for (Task task: this.tasks) {
            tasks.add(task.taskToString());
        }

        return tasks;
    }

    public boolean checkIfTaskExists(int index) {
        return index > 0 && index <= getNumberOfTask();
    }

    public int getNumberOfTask() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        return String.format("\nThere are currently %d task in the list.", getNumberOfTask());
    }
}
