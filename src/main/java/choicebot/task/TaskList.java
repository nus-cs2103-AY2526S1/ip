package choicebot.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;


/**
 * Represents a collection of Task objects.
 */
public class TaskList {
    /** The list of tasks. */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty Tasklist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        assert this.tasks.contains(task) : "Task was not added properly.";
    }

    /**
     * Removes a Task from the task list.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
        assert !this.tasks.contains(task) : "Task was not deleted properly.";
    }

    /**
     * Returns the current task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the current task list size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if task list is empty.
     * @return True if task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a Task by its index in the task list.
     *
     * @param taskNumber Index of the task to be returned.
     */
    public Task getTask(Integer taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Finds all tasks that match the given keyword.
     *
     * @param keyword Keyword to search for in taskList.
     * @return List of all tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the number of tasks in task list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Sorts the {@link Event tasks} in task list according to the start time.
     *
     * @return Returns list of sorted {@link Event tasks}.
     */
    public ArrayList<Task> sortEventTasks() {
        assert tasks != null : "Task list cannot be empty.";
        return tasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .sorted(Comparator.comparing(Event::getStartDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts the {@link Todo tasks} in task list according to alphabetical order.
     *
     * @return Returns list of sorted {@link Todo tasks}.
     */
    public ArrayList<Task> sortTodoTasks() {
        assert tasks != null : "Task list cannot be empty.";
        return tasks.stream()
                .filter(task -> task instanceof Todo)
                .map(task -> (Todo) task)
                .sorted(Comparator.comparing(Todo::getDescription))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts the {@link Deadline tasks} in task list according to the due date.
     *
     * @return Returns list of sorted {@link Deadline tasks}.
     */
    public ArrayList<Task> sortDeadlineTasks() {
        assert tasks != null : "Task list cannot be empty.";
        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .sorted(Comparator.comparing(Deadline::getDueDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
