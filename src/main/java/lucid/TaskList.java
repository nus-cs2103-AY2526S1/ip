package lucid;

import java.util.ArrayList;

/**
 * Represents a list of Task objects, implemented with an ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises the tasklist according to a data file. If no data file is present, creates a new empty task list
     */
    public TaskList() {
        Storage data = new Storage();
        this.tasks = data.readData();
    }

    /**
     * Adds a task to the TaskList
     * @param task Task to add
     */
    public String addTask(Task task) {
        tasks.add(task);
        String out = "";
        out += Ui.taskAddedMessage(task);
        out += Ui.numberOfTasksMessage(tasks.size());

        Storage data = new Storage();
        data.appendTaskData(task);
        return out;
    }

    /**
     * Prints all tasks currently in the list
     */
    public String printTasks() {
        int size = this.tasks.size();
        String out = "Here are all your tasks:\n";
        for (int i = 1; i <= size; i++) {
            out += Ui.printTaskInFoundList(i, this.tasks.get(i - 1)) + "\n";
        }
        return out;
    }

    /**
     * Completes a task in the list
     * @param index Index of task to complete
     */
    public String markTaskAsComplete(int index) {
        if (isInvalidIndex(index)) {
            return "That's not a valid task index!";
        }
        Task task = this.tasks.get(index - 1);
        if (task.isComplete()) {
            return Ui.taskAlreadyCompletedMessage();
        } else {
            Storage data = new Storage();
            task.markAsComplete();
            assert task.isComplete() : "task should be complete";
            data.markTaskDataComplete(index);
            return Ui.taskCompletedMessage(task);
        }
    }

    /**
     * Marks a task in the list as not complete
     * @param index Index of task to mark as not complete
     */
    public String markTaskAsNotComplete(int index) {
        if (isInvalidIndex(index)) {
            return "That's not a valid task index!";
        }
        Task task = this.tasks.get(index - 1);
        if (!task.isComplete()) {
            return Ui.taskNotCompletedYetMessage();
        } else {
            Storage data = new Storage();
            task.markAsNotComplete();
            assert !task.isComplete() : "task should be not complete";
            data.markTaskDataNotComplete(index);
            return Ui.taskUncompletedMessage(task);
        }
    }

    /**
     * Deletes a task from the list
     * @param index Index of task to delete
     */
    public String deleteTask(int index) {
        if (isInvalidIndex(index)) {
            return "That's not a valid task index!";
        }
        String out = "";
        out += Ui.taskDeletedMessage(this.tasks.get(index - 1));
        this.tasks.remove(index - 1);
        Storage data = new Storage();
        data.deleteTaskData(index);
        out += Ui.numberOfTasksMessage(tasks.size());
        return out;
    }

    /**
     * Checks if the given index is valid for the current number of existing tasks
     * @param index Index to check for validity
     * @return false if index < 0 or index > TaskList size, true otherwise
     */
    public boolean isInvalidIndex(int index) {
        if (index > tasks.size() || index <= 0) {
            Ui.invalidTaskIndexMessage();
            return true;
        }
        return false;
    }

    /**
     * Finds task names containing a substring and prints them
     * @param s Substring to search for in task names
     */
    public String findAndPrintTasks(String s) {
        int count = 1;
        String out = "";
        out += Ui.tasksFoundMessage();
        for (Task t : tasks) {
            if (t.getName().contains(s)) {
                out += "\n" + Ui.printTaskInFoundList(count, t);
                count++;
            }
        }
        return out;
    }
}
