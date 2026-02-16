package meo.data;
import java.util.ArrayList;

import meo.FileHandler;
import meo.MeoException;
import meo.task.Task;

/** 
 * Contains the currents task list.
 * Processes any actions regarding the task list.
 */
public class TextList {
    ArrayList<Task> taskList = new ArrayList<Task>(); 

    public TextList(){

    };

    public TextList(ArrayList<Task> list) {
        taskList = list;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Prints every task in the current task list with their indices
     */
    public void printList() {
        int index = 0;
        while (index < taskList.size()) {
            System.out.println(index + 1 + ". " + taskList.get(index).toString());
            index++;
        }
    }

    public String getList() {
        String list = "";
        int index = 0;
        while (index < taskList.size()) {
            list += index + 1 + ". " + taskList.get(index).toString() + System.lineSeparator();
            index++;
        }
        return list;
    }

    public void sortList(int compareIndex) {
        taskList.sort((task1, task2) -> { return compareIndex * task1.getDescription().compareTo(task2.getDescription());});
    }

    /**
     * Saves the current task list to data file
     */
    public void saveList() {
        FileHandler.writeFile(taskList);
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Marks a task as done
     * 
     * @param index Index of targeted task
     * @throws MeoException If the targeted task is not found in the task list
     */
    public void markTask(int index) throws MeoException{
        assert index >= 0 : "Index must be positive";
        Task task = taskList.size() == 0 ? null : taskList.get(index);
        if (task != null) {
            task.isMarked();
        } else {
            throw new MeoException(MeoException.taskNotExist);
        }
    }

    /**
     * Marks a task as not done
     * 
     * @param index Index of targeted task
     * @throws MeoException If the targeted task is not found in the task list
     */
    public void unmarkTask(int index) throws MeoException {
        assert index >= 0 : "Index must be positive";
        Task task = taskList.size() == 0 ? null : taskList.get(index);
        if (task != null) {
            task.isUnmarked();
        } else {
            throw new MeoException(MeoException.taskNotExist);
        }
    }

    /**
     * Delete a task
     * 
     * @param index Index of targeted task
     * @throws MeoException If the targeted task is not found in the task list
     */
    public void deleteTask(int index) throws MeoException{
        Task task = taskList.size() == 0 ? null : taskList.get(index);
        if (task != null) {
            taskList.remove(index);
        } else {
            throw new MeoException(MeoException.taskNotExist);
        }
    }

    /**
     * Returns a targeted task as a formatted string
     * 
     * @param index Index of targeted task
     * @return Formatted string of argeted task
     */
    public String printTask(int index) {
        return taskList.get(index).toString();
    }

    public ArrayList<Task> getAllTask() {
        return taskList;
    }
}
