package task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import ineffaexceptions.IneffaException;

/**
 * Contains all the tasks in bot
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns int value of indexStr - 1
     * As 1-based indexing used by user commands
     * Validates that indexStr can be parsed as an int.
     * Validates that index can be found in tasks array.
     *
     * @param indexStr string format of index in tasks.
     * @return int value of valid indexStr
     * @throws NumberFormatException If indexStr cannot be parsed as int
     * @throws IneffaException If index value < 0 or greater than tasks array size.
     */
    public int validateTasksIndex(String indexStr) throws IneffaException, NumberFormatException {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= this.tasks.size() || index < 0) {
            throw new IneffaException("Invalid index number");
        }
        return index;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int taskSize() {
        return tasks.size();
    }

    public ArrayList<Task> list() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Returns tasks based on task description that user wants to see.
     * Passing empty string to description prints all tasks.
     *
     * @param description Description that user wants to see from TASKS.
     */
    public ArrayList<Task> getAllTasks(String description) {
        ArrayList<Task> tmpTasks = this.tasks;
        if (!description.isBlank()) {
            tmpTasks = tmpTasks
                    .stream()
                    .filter(task -> task.containsDescription(description))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (tmpTasks.isEmpty()) {
            return new ArrayList<>();
        }
        return tmpTasks;
    }
}
