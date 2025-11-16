package tuesday.task;


import tuesday.task.TaskEnums.TaskType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public int size() {
        return tasks.size();
    }
    public TaskList filterTaskByType(TaskType type) {
        Stream<Task> stream = this.tasks.stream();
        return new TaskList(stream.filter(task -> task.getTaskType() == type).toList());
    }

    public TaskList filterTaskByTime(TaskType type) {
        List<Task> filterTasks = this.filterTaskByType(type).getTasks();
        return new TaskList(filterTasks.stream().sorted(Comparator.comparing(Task::getLDTTime)).toList());
    }
}
