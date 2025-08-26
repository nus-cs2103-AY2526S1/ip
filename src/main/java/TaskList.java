import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> initialTasks) {
        this.tasks = new ArrayList<>(initialTasks);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> asList() {
        return tasks;
    }

    public Task get(int index) throws ByteException {
        if (index < 0 || index >= tasks.size()) {
            throw new ByteException("Task number is invalid");
        }
        return tasks.get(index);
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task delete(int index) throws ByteException {
        Task removed = get(index);
        tasks.remove(index);
        return removed;
    }

    public void mark(int index) throws ByteException {
        Task task = get(index);
        task.mark();
    }

    public void unmark(int index) throws ByteException {
        Task task = get(index);
        task.unmark();
    }

    public void writeToFile(FileWriter writer) throws IOException {
        for (Task task : tasks) {
            String status = task.isDone ? "1" : "0";
            writer.write(status + " | " + task + "\n");
        }
    }
}


