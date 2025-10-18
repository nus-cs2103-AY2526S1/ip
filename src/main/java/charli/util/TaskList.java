package charli.util;

import charli.exception.CharliException;
import charli.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Manages a collection of tasks and provides operations for task management.
 * Handles adding, removing, and querying tasks in the list.
 */
public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }



    public String showRotation() {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("All done!!!\n");
        } else {
            message.append("INCREDIBLE MIX INCOMING!!! ")
                    .append(tasks.size())
                    .append(" tracks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append(i + 1).append(". ")
                        .append(tasks.get(i).toString()).append("\n");
            }
        }
        return message.toString();
    }

    public String getLastTaskString() {
        return tasks.get(tasks.size() - 1).toString();
    }

    public String getMatchingTasks(String keyword) throws CharliException {

        List<Task> matches = tasks.stream()
                .filter(t -> t.getDescription() != null)
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matches.isEmpty()) {
            throw new CharliException("No tracks found containing: " + keyword);
        }

        String body = IntStream.range(0, matches.size())
                .mapToObj(i -> (i + 1) + ". " + matches.get(i))
                .collect(Collectors.joining("\n"));

        return "Here are the matching tracks in your rotation:\n" + body;

    }
}
