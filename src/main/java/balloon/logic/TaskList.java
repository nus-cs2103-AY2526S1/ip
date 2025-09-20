package balloon.logic;

import java.util.ArrayList;

import balloon.exception.TaskNumberException;
import balloon.task.Task;

/**
 * Represents a list of tasks that the user has.
 * <p>
 * This class provides methods to manipulate tasks, such as adding, deleting,
 * marking, unmarking, and searching tasks. Commands in the Balloon program
 * interact with this class to modify the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @return the tasks as an ArrayList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a given task as done.
     *
     * @param index position of the task in the list.
     * @return the Task marked.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task markTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return markedTask;
    }

    /**
     * Unmarks a given task; meaning it is not done.
     *
     * @param index position of the task in the list.
     * @return the Task unmarked.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task unmarkTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.unmark();
        return unmarkedTask;
    }

    /**
     * Adds a Task to the back of the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds the task to the specified index in this TaskList, and shifts every
     * subsequent task in the list to the right by 1 (if any).
     */
    public void addTaskAtIndex(int index, Task task) {
        tasks.add(index, task);
    }


    /**
     * Deletes a task specified by the given index.
     *
     * @param index position of the task in the list.
     * @return the Task deleted.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task deleteTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves all the tasks in this TaskList whose description contains a certain keyword.
     *
     * @param keyword the word we are searching for.
     * @return a list of tasks that is a subset of all the tasks.
     */
    public ArrayList<Task> getTasksWithKeyword(String keyword) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsWord(keyword)) {
                output.add(task);
            }
        }
        return output;
    }

}
