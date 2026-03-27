package joe.task;

import java.util.ArrayList;

/**
 * List specifically for handling Task objects
 */
public class TaskList {
    private ArrayList<Task> todoList;

    /**
     * Creates a list of tasks. Allows for direct manipulation of the tasks in the
     * list.
     * 
     * @param todoList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> todoList) {
        if (todoList == null) {
            this.todoList = new ArrayList<>();
        } else {
            this.todoList = todoList;
        }
    }

    /**
     * Adds a task to the TaskList object.
     * 
     * @param task Task object.
     */
    public String addToList(Task task) {
        this.todoList.add(task);
        return "Got it. I've added this task: \n" + task.toString() + "\nNow you have " + this.todoList.size()
                + " tasks in the list.";
    }

    /**
     * Deletes a task from the TaskList object.
     * 
     * @param index Index of the task to be deleted from the TaskList.
     */
    public String deleteFromList(int index) {
        Task task = this.todoList.get(index);
        this.todoList.remove(index);
        return "Got it. I've removed this task from your list: \n" + task.toString() + "\nNow you have "
                + this.todoList.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done in the TaskList.
     * 
     * @param index Index of the task to be marked as done.
     */
    public String markTaskAsDone(int index) {
        assert index >= 0 : "Index of task cannot be negative";
        String output = "";
        if (index > this.todoList.size()) {
            return "Invalid task number. Please try again.";
        } else {
            output += "Ok, I've marked the task as done: \n";
            Task task = todoList.get(index - 1);
            task.markAsDone();
            output += task.toString();
            return output;
        }
    }

    /**
     * Marks a task as not done in the TaskList.
     * 
     * @param index Index of the task to be marked as not done.
     */
    public String markTaskAsNotDone(int index) {
        assert index >= 0 : "Index of task cannot be negative";
        String output = "";
        if (index > this.todoList.size()) {
            return "Invalid task number. Please try again.";
        } else {
            output += "Ok, I've marked the task as not done: \n";
            Task task = todoList.get(index - 1);
            task.markAsNotDone();
            output += task.toString();
            return output;
        }
    }

    public ArrayList<Task> getTodoList() {
        return this.todoList;
    }

    public int getLength() {
        return this.todoList.size();
    }

    public Task getTask(int index) {
        return this.todoList.get(index);
    }

    public Task getSoonestTask() {
        Task soonestTask = null;

        for (Task task : todoList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getNextDate() == null)
                    continue;
                if (soonestTask == null || deadline.getNextDate().compareTo(soonestTask.getNextDate()) < 0) {
                    soonestTask = deadline;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getNextDate() == null)
                    continue;
                if (soonestTask == null || event.getNextDate().compareTo(soonestTask.getNextDate()) < 0) {
                    soonestTask = event;
                }
            }
        }

        return soonestTask;
    }
}
