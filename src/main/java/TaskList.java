import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index - 1).setDone();
    }

    public void markTaskAsNotDone(int index) {
        this.tasks.get(index - 1).setNotDone();
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public void removeTask (int index) {
        this.tasks.remove(index - 1);
    }

    public String listAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            String taskFormatted = String.format(" %d. %s\n", i + 1, currentTask);
            stringBuilder.append(taskFormatted);
        }

        return stringBuilder.toString();
    }

    public int getNumberOfTask() {
        return this.tasks.size();
    }
}
