package geni.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to add, get, delete tasks, and check the size of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */

    public TaskList() {

        this.tasks = new ArrayList<>();
    }
    /**
     * Creates a TaskList with the given list of tasks.
     *
     * @param tasks initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "TaskList constructor: tasks must not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index of the task
     * @return task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }
    /**
     * Removes and returns the task at the specified index.
     *
     * @param index index of the task to remove
     * @return the removed task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Finds all tasks whose description contains the given keyword.
     *
     * @param keyword substring to look for in task descriptions
     * @return list of matching tasks, or an empty list if none found
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Finds a free continuous time slot of at least the given number of hours
     * that does not overlap with any busy intervals from existing tasks.
     *
     * @param hours required duration in hours
     * @return description of the free slot, or {@code null} / message if none available
     */
    public String findFreeSlot(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("hours must be positive");
        }
        long neededMinutes = hours * 60L;
        List<LocalDateTime[]> busy = new ArrayList<>();
        for (Task t : tasks) {
            busy.addAll(t.getBusyIntervals());
        }

        busy.sort(Comparator.comparing(slot -> slot[0]));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime prevEnd = now;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String freeTime = "Nearest free " + hours + "h slot: "
                + prevEnd.format(formatter) + " to " + prevEnd.plusMinutes(neededMinutes).format(formatter);

        for (LocalDateTime[] slot : busy) {
            if (slot[1].isBefore(now)) {
                continue;
            }
            LocalDateTime start = slot[0].isBefore(now) ? now : slot[0];
            long gapMinutes = Duration.between(prevEnd, start).toMinutes();
            if (gapMinutes >= neededMinutes) {
                return freeTime;
            }
            prevEnd = slot[1].isAfter(prevEnd) ? slot[1] : prevEnd;
        }

        return freeTime;
    }

}
