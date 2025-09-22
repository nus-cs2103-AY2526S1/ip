package broccoli;

import broccoli.Tasks.Task;

import java.util.ArrayList;

/**
 * Manages tasks collection and operation.
 * Provides methods to add, remove, and display tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the task list.
     * The task is appended to the end of the current list.
     *
     * @param task The task object to be added to the list
     */
    public void add(Task task){
        this.taskList.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The zero-based index of the task to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int index){
        this.taskList.remove(index);
    }

//    /**
//     * Prints all tasks in the list with order .
//     */
//    public void printTask() {
//        int counter = 1;
//        for(Task task : taskList) {
//            System.out.println(counter + ". " + task.toString());
//            counter++;
//        }
//    }

    /**
     * Returns the underlying ArrayList containing all tasks.
     * Provides direct access to the task collection for external manipulation.
     *
     * @return The ArrayList of Task objects managed by this TaskList
     */
    public String displayTask() {
        int counter = 1;
        String output = "";
        for(Task task : taskList) {
           output = output + "\n"+ counter + ". " + task.toString();
            counter++;
        }
        return output;
    }
    public ArrayList<Task> getList(){
        return this.taskList;

    }

    public boolean isEmptyList(){
        return taskList.isEmpty();
    }
}
