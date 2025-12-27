package fatty;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fatty.task.EventTask;
import fatty.task.Task;


/**
 * Tracks the tasks of the user.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Takes in Task object and adds to the List
     *
     * @param task Task to be added
     * @throws FattyException No task created or tasklist is full.
     */
    public void addTask(Task task) throws FattyException {
        boolean isNullTask = task == null;
        boolean isFullTasks = tasks.size() > 100;

        if (isNullTask) {
            throw new FattyException("Task cannot be empty!");
        }
        if (isFullTasks) {
            throw new FattyException("Task list is full!");
        }

        //clashing task is a task in tasklist that clashes with new task
        Task clashingTask = detectAnomalies(task);
        if (clashingTask != null) {
            String message = "Task clashes with " + clashingTask + ".";
            throw new FattyException(message);
        }
        tasks.add(task);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index One Indexed index
     * @return Task at index.
     * @throws FattyException For invalid indexes.
     */
    public Task get(int index) throws FattyException {
        boolean isInvalidIndex = index < 1 || index > tasks.size();

        if (tasks.isEmpty() || isInvalidIndex) {
            throw new FattyException("Invalid task Number!");
        }

        return tasks.get(index - 1);
    }

    /**
     * Deletes task at given index.
     * @param index index of Task
     * @throws FattyException For invalid index.
     */
    public void delete(int index) throws FattyException {
        boolean isInvalidIndex = index < 1 || index > tasks.size();

        if (tasks.isEmpty() || isInvalidIndex) {
            throw new FattyException("Invalid task Number!");
        }

        tasks.remove(index - 1);
    }

    /**
     * Marks tasks at given index.
     * @param index Index of task.
     * @throws FattyException For invalid index.
     */
    public void mark(int index) throws FattyException {
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).mark();
    }

    /**
     * Unmarks task at given index.
     * @param index 1 index of tasks in tasklist
     * @throws FattyException Invalid index number
     */
    public void unmark(int index) throws FattyException {
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).unmark();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Return the TaskList in appropriate String Form.
     * 1. Task...
     * 2. Task...
     * ...
     *
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            sb.append(taskNum)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        return sb.toString().trim();
    }

    /**
     * Find tasks with descriptions that contains description.
     * @param keyword search for tasks with keyword in description
     * @return A list of tasks that contain the keyword
     */
    public TaskList find(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Checks for Event tasks in tasklist that are UNMARKKED and clashes with new Task
     * @param task New task to be added
     * @return null if no clashes, else returns existing task that clashes with event
     */
    private Task detectAnomalies(Task task) {
        boolean isNotEventTask = !(task instanceof EventTask);
        if (isNotEventTask) {
            return null;
        }

        for (Task existing : tasks) {
            // Only compare with unmarked EventTasks
            boolean isUnmarkedEventTask = existing instanceof EventTask && !existing.isMark();
            if (isUnmarkedEventTask) {
                if (isClashing((EventTask) task, (EventTask) existing)) {
                    return existing;
                }
            }
        }

        return null;
    }

    /**
     * Check if two EventTasks overlap in time.
     * @param e1
     * @param e2
     * @return
     */
    private boolean isClashing(EventTask e1, EventTask e2) {
        return e1.getFrom().isBefore(e2.getTo()) && e1.getTo().isAfter(e2.getFrom());
    }
}

