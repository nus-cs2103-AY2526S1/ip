package rafayel.task;

import java.util.ArrayList;

import rafayel.RafayelException;
import rafayel.Storage;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public boolean checkTaskNumber(int taskNumber) throws RafayelException {
        if (taskNumber < 0 || taskNumber >= this.getSize()) {
            throw new RafayelException("Invalid task number.");
            // return false;
        } else {
            return true;
        }
    }

    public Task get(int taskNumber) throws RafayelException {
        if (checkTaskNumber(taskNumber)) {
            return tasks.get(taskNumber);
        }
        return null;
    }

    public Task remove(int taskNumber) throws RafayelException {
        if (checkTaskNumber(taskNumber)) {
            return tasks.remove(taskNumber);
        }
        return null;
    }

    public void getTaskList() {
        if (this.getSize() == 0) {
            System.out.println("There's nothing in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.getSize(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
                // System.out.println(String.format("%d. %s", i + 1, data[i]));
            }
        }
    }

    // public void saveTasks(Storage storage) throws Exception {
    //     storage.save(this.tasks);
    // }

    public ArrayList<Task> getAll() {
        return this.tasks;
    }

}
