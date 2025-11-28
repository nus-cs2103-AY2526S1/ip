package penguin.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import penguin.tasks.Deadline;
import penguin.tasks.Event;
import penguin.tasks.Task;
import penguin.tasks.Todo;

/**
 * The class that keeps track of the tasks in the list and performs operations
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Useful for pretty printing all tasks in the list
     * @return all tasks in the list
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * @return Number of tasks in the list
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * @param idx index of the task in the list
     * @return task at specified index
     */
    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * adds task to the list
     * @param task task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * deletes task at specified index
     * @param idx index of task to be deleted to the list
     */
    public Task deleteTask(int idx) {
        return tasks.remove(idx);
    }

    /**
     * marks task at specified index as done
     * @param idx index of task in list
     */
    public void markAsDone(int idx) {
        tasks.get(idx).markAsDone();
    }

    /**
     * marks task at specified index as undone
     * @param idx index of task in list
     */
    public void markAsUndone(int idx) {
        tasks.get(idx).markAsUndone();
    }

    /**
     * @param keyword keyword to search for
     * @return list of tasks containing keyword
     */
    public List<Task> findTasks(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    /**
     * @param isAscending whether earliest tasks first
     * @return list of tasks arranged by date
     */
    public List<Task> sortTasksByDate(boolean isAscending) {
        // Add todos to the front first
        List<Task> result = this.tasks.stream()
                .filter(task -> task instanceof Todo)
                .collect(Collectors.toList());

        List<Task> timedTasks = this.tasks.stream()
                .filter(task -> !(task instanceof Todo))
                .collect(Collectors.toList());

        // Sort timed tasks
        Comparator<Task> comparator = Comparator.comparing(task -> {
            if (task instanceof Deadline) {
                return ((Deadline) task).getDeadline();
            } else if (task instanceof Event) {
                return ((Event) task).getStart();
            } else {
                return null;
            }
        });

        // Reverse order for descending
        if (!isAscending) {
            comparator = comparator.reversed();
        }

        timedTasks.sort(comparator);

        // Append timed tasks to todos
        result.addAll(timedTasks);
        return result;
    }
}
