package chirp.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chirp.exceptions.ChirpException;
import chirp.exceptions.TaskListOutOfBoundsException;

/**
 * Manages list of tasks
 */
public class TaskList {

    private ArrayList<Task> tasks; // List of task descriptions

    /**
     * Creates a task list object to manage list of tasks
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds tasks to task list
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list
     * @param idx 0-Index of the task to delete
     * @return Task object deleted
     * @throws ChirpException
     */
    public Task deleteTask(int idx) throws ChirpException {
        checkValidIdx(idx);
        return tasks.remove(idx);
    }

    /**
     * Marks a task in the task list as done/not done
     *
     * @param idx    Index of task to mark (0-indexed)
     * @param isDone Whether to mark the task as done or not done
     */
    public void markTask(int idx, boolean isDone) throws ChirpException {
        checkValidIdx(idx);
        tasks.get(idx).setDone(isDone);
    }

    /**
     * Gets task in list
     *
     * @param idx Index of task to retrieve (0-indexed)
     * @return Requested task
     */
    public Task getTask(int idx) throws ChirpException {
        checkValidIdx(idx);
        return tasks.get(idx);
    }

    /**
     * Gets num of tasks in list
     *
     * @return Num of tasks in list
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns display string filtered by date
     *
     * @return Human-readable string of list of task objects
     */
    public String displayStr(LocalDate date) {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < getNumOfTasks(); i++) {
            if (tasks.get(i).validForDate(date)) {
                data.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
        }
        return data.toString();
    }

    /**
     * Returns display string filtered by substring
     *
     * @param filter Filter string
     * @return String containing list of tasks that contains the filter
     *     string in the descriptions.
     */
    public String displayStr(String filter) {
        String data = IntStream.range(0, getNumOfTasks())
                .filter(i -> tasks.get(i).containsStr(filter))
                .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                .collect(Collectors.joining("\n"));
        return data.toString() + "\n";
    }

    private void checkValidIdx(int idx) throws TaskListOutOfBoundsException {
        if (idx < 0 || idx >= getNumOfTasks()) {
            throw new TaskListOutOfBoundsException(idx, getNumOfTasks());
        }
    }
}
