package tarawrr;
import java.util.ArrayList;

/**
 * TaskList contains the task list and has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private int counter = 0;

    /**
     * Constructor initialises an empty TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor initialises a TaskList given an ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
        this.counter = list.size();
    }

    public int numberOfTasks() {
        return this.counter;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Marks a Task from TaskList given its position
     */
    public void markTask(int index) {
       this.taskList.get(index - 1).complete();
    }

    /**
     * Unmarks a Task from TaskList given its position
     */
    public void unmarkTask(int index) {
        this.taskList.get(index - 1).uncomplete();
    }

    /**
     * Adds a Task to TaskList
     */
    public void addToTaskList(Task task) {
        this.taskList.add(task);
        counter++;
    }

    //Removes a Task from TaskList given its positio
    public void removeFromTaskList(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.remove(taskIndex);
        counter--;
    }

    // Empty the task list
    public void clear() {
        this.taskList.clear();
        this.counter = 0;
    }

    @Override
    public String toString() {
        String logbookString = "";
        for (int i = 1; i <= this.numberOfTasks(); i++) {
            logbookString += i + ". " + this.taskList.get(i - 1).toString() + "\n";
        }
        return logbookString;
    }

}




