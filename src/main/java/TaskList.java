import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(String description) {
        this.list.add(new Task(description));
    }

    public void mark(int i) {
        this.list.get(i - 1).markTask();
    }

    public void unmark(int i) {
        this.list.get(i - 1).unmarkTask();
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
