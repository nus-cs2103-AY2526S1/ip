import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void delete(int i) {
        this.list.remove(i - 1);
    }

    public void mark(int i) {
        this.list.get(i - 1).markTask();
    }

    public void unmark(int i) {
        this.list.get(i - 1).unmarkTask();
    }

    public Task get(int i) {
        // Get the ith Task in the list
        return this.list.get(i - 1);
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString().trim();
    }
}
