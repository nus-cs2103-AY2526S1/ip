import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int i) {
        return this.tasks.remove(i);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }
}
