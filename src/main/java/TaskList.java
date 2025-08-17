import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask (String name) {
        this.tasks.add(new Task(name));
    }

    public String listAllTasks () {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            stringBuilder.append(String.format(" %d. %s\n", i + 1, this.tasks.get(i)));
        }

        return stringBuilder.toString();
    }
}
