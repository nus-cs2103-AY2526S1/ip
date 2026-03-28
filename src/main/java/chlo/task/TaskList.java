package chlo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chlo.exception.ChloException;

/**
 * TaskList class that contains a task arraylist
 * This acts like an arraylist with more robust list display features
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) throws ChloException {
        try {
            return tasks.get(index);
        } catch (Exception e) {
            throw new ChloException("Index not valid.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public String getTasks() {
        return IntStream.range(0, tasks.size()).mapToObj(i -> (i + 1) + ". " + tasks.get(i)).collect(Collectors.joining("\n"));
    }

    public String getFilteredTasks(String s) {
        String result = IntStream.range(0, tasks.size()).filter(i -> tasks.get(i).getDescription().contains(s)).mapToObj(i -> (i + 1) + ". " + tasks.get(i)).collect(Collectors.joining("\n"));

        return result.isEmpty() ? "No such task found." : result;
    }

    /**
     * sorts tasks with todo at the end. Deadline and events sorted by respective by and from times
     */
    public void sort() {
        Collections.sort(tasks, (a, b) -> {
            // Handle a and b tasks if either is a Todo object
            if (a instanceof Todo && !(b instanceof Todo)) {
                return 1;
            } else if (b instanceof Todo && !(a instanceof Todo)) {
                return -1;
            } else if (a instanceof Todo) {
                return 0;
            }

            // Neither Todo
            LocalDateTime aTime = (a instanceof Event ? ((Event) a).getFrom() : ((Deadline) a).getBy());
            LocalDateTime bTime = (b instanceof Event ? ((Event) b).getFrom() : ((Deadline) b).getBy());
            return aTime.compareTo(bTime);
        });
    }


}
