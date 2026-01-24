package noob.task;

import java.util.ArrayList;
import java.util.List;

import noob.exception.NoobException;
import noob.storage.Storage;

/**
 * Stores a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int numTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.numTasks = tasks.size();
    }

    public TaskList(Storage storage) throws NoobException {
        this.tasks = storage.getListOfTasks();
        this.numTasks = this.tasks.size();
    }

    /**
     * Gets the specified task number from the TaskList
     *
     * @param i 1-based index task number to be retrieved
     * @return Task numbered i
     * @throws NoobException If input integer is out of range of the size of TaskList
     */
    public Task getTask(int i) throws NoobException {
        if (i <= 0 || i > numTasks) {
            throw new NoobException("Task number " + i + "does not exist");
        }
        return this.tasks.get(i - 1);
    }

    /**
     * Adds input task to the TaskList
     *
     * @param task Task to be added to TaskList
     * @return The number of items in the TaskList
     */
    public int addTask(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        return this.numTasks;
    }

    /**
     * Deletes the specified task number from the TaskList
     *
     * @param i 1-based index task number to be deleted
     * @return The deleted Task
     * @throws NoobException If input integer is out of range of the size of TaskList
     */
    public Task deleteTask(int i) throws NoobException {
        if (this.numTasks == 0) {
            throw new NoobException("No tasks to delete");
        }

        if (i <= 0 || i > this.numTasks) {
            throw new NoobException("Task number " + i + " does not exist");
        }

        Task task = this.tasks.remove(i - 1);
        this.numTasks--;
        return task;
    }

    /**
     * Marks the specified task as isDone
     *
     * @param i Task number to be marked
     * @param isDone True if task is to be marked as done, false otherwise
     * @return Task that is marked
     * @throws NoobException If input integer is out of range of the size of TaskList
     */
    public Task markTask(int i, boolean isDone) throws NoobException {
        if (i <= 0 || i > this.numTasks) {
            throw new NoobException("No task numbered " + i);
        }

        Task task = this.tasks.get(i - 1);

        if (isDone) {
            task.markDone();
            assert task.isDone() : "Failed to mark task as done";
        } else {
            task.unmarkDone();
            assert !task.isDone() : "Failed to mark task as undone";
        }

        return task;
    }

    /**
     * Gets the number of tasks in the TaskList
     *
     * @return The number of tasks in the TaskList
     */
    public int getNumTasks() {
        return this.numTasks;
    }

    /**
     * Returns a list of tasks whose description contain the matching string
     *
     * @param matcher Search string to match task description
     * @return Filtered list of tasks that have description matching input string
     */
    public TaskList filterTasks(String matcher) {
        return new TaskList(this.tasks
                .stream()
                .filter(task -> task.getDesc().toLowerCase().contains(matcher.toLowerCase())).toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);

            sb.append(i + 1);
            sb.append(".");
            sb.append(task).append("\n");
        }

        return sb.isEmpty()
                ? "No tasks to display"
                : sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskList taskList) {
            try {
                if (this.numTasks != taskList.numTasks) return false;
                for (int i = 0; i < this.numTasks; i++) {
                    if (!this.getTask(i).equals(taskList.getTask(i))) {
                        return false;
                    }
                }
                return true;
            } catch (NoobException e) {
                return false;
            }
        }
        return false;
    }
}
