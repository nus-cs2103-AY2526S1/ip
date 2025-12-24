package pero.model;

import pero.exception.PeroException;
import pero.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for managing tasks in memory
 */
public class TaskList {

    /** Raw list of tasks within each TaskList instance. */
    private final List<Task> rawTaskList; //the raw task list, not object

    private Storage storage;

    /**
     * Constructor: creates empty task list
     */
    public TaskList() {
        this.rawTaskList = new ArrayList<>();
    }

    /**
     * Constructor: Loads list from storage, and initialises taskList.
     *
     * @param filePath Path of Pero_storage to load taskList from.
     * @throws IOException If file cannot be read.
     */
    public TaskList(String filePath) throws IOException{
        this.storage = new Storage(filePath);
        this.rawTaskList = storage.loadList().getAllTasks();
    }


    /**
     * Returns all the tasks in a list.
     *
     * @return Raw task list.
     */
    public List<Task> getAllTasks() {
        return rawTaskList;
    }

    /**
     * Add task from user input to TaskList tasks and returns the task.
     *
     * @param input Current line being read from input.
     * @return Current task being added.
     * @throws PeroException If line starts with unknown task type.
     */
    public Task addTaskFromInput(String input) throws PeroException {
        Task currTask;
        if (input.startsWith("todo")) {
            currTask = ToDo.fromInput(input);
        } else if (input.startsWith("deadline")) {
            currTask = Deadline.fromInput(input);
        } else if (input.startsWith("event")) {
            currTask = Event.fromInput(input);
        } else {
            throw new PeroException("Unknown task type identified, pls try again.");
        }
        rawTaskList.add(currTask);
        return currTask;
    }


    /**
     * Mark current task as done, and return current task.
     *
     * @param index Index to get current task in task list.
     * @return Current task.
     */
    public Task markTask(int index) {
        Task currTask = rawTaskList.get(index);
        currTask.markAsDone();
        return currTask;
    }

    /**
     * Mark current task as done, and return current task.
     *
     * @param index Index to get current task in task list.
     * @return Current task.
     */
    public Task unmarkTask(int index) {
        Task currTask = rawTaskList.get(index);
        currTask.markAsUndone();
        return currTask;
    }

    /**
     * Delete tasks and returns the deleted task.
     * @param index Index of task being deleted.
     * @return Deleted task.
     */
    public Task removeTask(int index) {
        return rawTaskList.remove(index);
    }

    /**
     * Adds a task directly to raw task list.
     * @param task Current task.
     */
    public void addTask(Task task) {
        rawTaskList.add(task);
    }

    /**
     * Extract all tasks containing relevant keyword and return in a TaskList
     *
     * @param keyword Keyword to find in each task.
     * @return TaskList containing tasks that match/contain the keyword.
     */
    public TaskList findTasks(String keyword){
        TaskList matchedTasks = new TaskList();
        for (Task t : rawTaskList) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())){
                matchedTasks.addTask(t);
            }
        }
        return matchedTasks;
    }
}
