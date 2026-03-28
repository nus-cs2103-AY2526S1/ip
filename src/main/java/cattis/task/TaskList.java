package cattis.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

/**
 * A class that encapsulate the actual list of tasks
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add new task to the list
     *
     * @param task the <code>Task</code> instance to be added in the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieve the task from the specific index
     * @param index one-indexed position to retrieve from the list
     * @return <code>Task</code> instance
     */
    public Task get(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        assert index >= 1 && index <= this.count();
        return this.tasks.get(index - 1);
    }

    /**
     * Mark the task from the specific index as finished.
     * @param index one-indexed position to mark as finished
     */
    public void mark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        assert index >= 1 && index <= this.count();
        this.tasks.get(index - 1).mark();
    }

    /**
     * Mark the task from the specific index as unfinished.
     * @param index one-indexed position to mark as unfinished
     */
    public void unmark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        this.tasks.get(index - 1).unmark();
    }

    /**
     * Delete the task from the specific index
     * @param index one-indexed position to delete from the list
     * @return <code>Task</code> instance that has been deleted
     */
    public Task delete(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        Task deletedTask = this.tasks.get(index - 1);
        assert index >= 1 && index <= this.count();
        assert deletedTask != null;
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Quickly prints out the number of elements in the list
     */
    public void taskListSummary(CattisInterface cattis) {
        String msg = Constants.SUMMARY_TASK_MSG;
        cattis.getUi().showMessage(String.format(msg, this.count()));
    }

    /**
     * Encode task list as string to be saved as text file
     * @return the encoded string
     */
    public String toEncodedString() {
        return tasks.stream()
                .map(Task::toEncodedString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return Constants.EMPTY_MSG;
        }
        return IntStream.range(0, this.tasks.size())
                .mapToObj(index -> String.format("%s. %s", index + 1, this.tasks.get(index)))
                .collect(Collectors.joining("\n"));
    }

    public int count() {
        return this.tasks.size();
    }


    /**
     * Retrieve all tasks from the specific date
     * For {@code DeadlineTask}, include this task if the deadline is {@code date}
     * For {@code EventTask}, include this task if start date or end date is {@code date}
     * @param date target date
     * @param hasTodo specify whether to include {@code TodoTask} or not
     * @return targeted tasks
     */
    public List<Task> getTasksByDate(LocalDate date, boolean hasTodo) {
        return this.tasks.stream()
                .filter(task -> {
                    if (task == null) {
                        return hasTodo;
                    }
                    if (task instanceof DeadlineTask) {
                        return ((DeadlineTask) task).isEqualDate(date);
                    }
                    if (task instanceof EventTask) {
                        return ((EventTask) task).isEqualDate(date);
                    }
                    return false;
                }).toList();
    }

    /**
     * Search all tasks whose task name contains the keyword <code>name</code>
     * to summarize all tasks as string.
     * @param name keyword to search
     * @return list of filtered tasks concatenated as string
     */
    public String listByName(String name) {
        StringBuilder result = new StringBuilder();
        int index = 1;

        for (Task task : this.tasks) {
            if (task.getTaskName().toLowerCase().contains(name.toLowerCase())) {
                result.append(index).append(". ").append(task).append("\n");
            }
            index++;
        }
        return result.toString();
    }
}
