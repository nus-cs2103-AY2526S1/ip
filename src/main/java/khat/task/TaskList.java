package khat.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Represents a list of tasks. */
public class TaskList {

    protected ArrayList<Task> tasks;

    /** Constructs an empty TaskList */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with given list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns Task at the given index.
     *
     * @param index Index of task to retrieve.
     * @return Task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns true if TaskList is empty.
     *
     * @return True if TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (tasks.isEmpty());
    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Filters all deadline tasks on a specified deadline date.
     *
     * @param date Date to filter deadlines by.
     */
    public TaskList getTasksOnDate(LocalDate date) {
        assert date != null : "Date to filter cannot be null";
        List<Task> filtered = tasks.stream()
                .filter(task -> task instanceof Deadline)
                .filter(task -> {
                    Deadline d = (Deadline) task;
                    return d.hasTime()
                            ? d.dateTime.toLocalDate().equals(date)
                            : d.date.equals(date);
                })
                .toList();
        return new TaskList(new ArrayList<>(filtered));
    }

    /**
     * Filters all tasks with the specified keyword.
     *
     * @param keyword Keyword to filter tasks by.
     */
    public TaskList getTasksWithKeyword(String keyword) {
        List<Task> filtered = tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .toList();
        return new TaskList(new ArrayList<>(filtered));
    }

    /**
     * Checks if a Task already exists in TaskList.
     *
     * @param newTask Task to be added
     * @return True if Tasks exists, false otherwise.
     */
    public boolean isDuplicate(Task newTask) {
        for (Task t : tasks) {
            if (t instanceof Todo && newTask instanceof Todo) {
                if (t.getDescription().equalsIgnoreCase(newTask.getDescription())) {
                    return true;
                }
            } else if (t instanceof Deadline && newTask instanceof Deadline) {
                Deadline d1 = (Deadline) t;
                Deadline d2 = (Deadline) newTask;
                boolean descMatch = d1.getDescription().equalsIgnoreCase(d2.getDescription());
                boolean dateMatch = d1.hasTime() == d2.hasTime() && (d1.hasTime()
                                ? d1.dateTime.equals(d2.dateTime)
                                : d1.date.equals(d2.date));
                if (descMatch && dateMatch) {
                    return true;
                }
            } else if (t instanceof Event && newTask instanceof Event) {
                Event e1 = (Event) t;
                Event e2 = (Event) newTask;
                boolean descMatch = e1.getDescription().equalsIgnoreCase(e2.getDescription());
                boolean fromMatch = e1.hasTime() == e2.hasTime() && (e1.hasTime()
                                ? e1.fromDateTime.equals(e2.fromDateTime)
                                : e1.fromDate.equals(e2.fromDate));
                boolean toMatch = e1.hasTime() == e2.hasTime() && (e1.hasTime()
                                ? e1.toDateTime.equals(e2.toDateTime)
                                : e1.toDate.equals(e2.toDate));
                if (descMatch && fromMatch && toMatch) {
                    return true;
                }
            }
        }
        return false;
    }
}
