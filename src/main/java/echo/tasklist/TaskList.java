package echo.tasklist;

import java.util.ArrayList;
import java.util.List;

import echo.task.Task;
/**
 * Represents a task list. A <code>TaskList</code> object stores the list of tasks.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList(List<? extends Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Adds a <code>Task</code> into the stored list of tasks.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes task with the corresponding index - 1 from the stored
     * list of tasks and returns it.
     *
     * @param index Position of task to be removed from the list, indexed at 1.
     * @return Task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Returns number of items in list of task.
     *
     * @return size Number of items in list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the task with the corresponding index - 1
     *
     * @param index Position of task to be returned from the list, indexed at 1.
     * @return Task at the corresponding index
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns a filtered task list based on whether keyword exists in description of task
     *
     * @param keyword Word to be found
     * @return TaskList
     */
    public TaskList getTasksWithKeyword(String keyword) {
        return new TaskList(this.taskList.stream().filter(task -> task.hasKeyword(keyword)).toList());
    }

    /**
     * Prints every task in the list.
     *
     * @return String containing all tasks
     */
    public static String printList(TaskList list) {
        int index = 1;
        StringBuilder msg = new StringBuilder();
        List<Task> tasks = list.getList();

        for (Task task : tasks) {
            msg.append(index).append(".").append(task).append("\n");
            index++;
        }
        return msg.toString();
    }

    /**
     * Returns a TaskList with tasks sorted in type order: To-do, Deadline, Event
     *
     * @return sorted TaskList
     */
    public TaskList getSortedListBasedOnType() {
        this.taskList.sort((o1, o2) -> {
            Integer val1 = o1.getOrder();
            Integer val2 = o2.getOrder();
            return Integer.compare(val1, val2);
        });
        return this;
    }
}
