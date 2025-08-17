import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask (String name) {
        this.tasks.add(new Task(name));
    }

    public void listAllTasks () {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d. %s", i + 1, this.tasks.get(i));
        }
    }
}
