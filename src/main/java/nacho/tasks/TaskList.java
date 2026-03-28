package nacho.tasks;

import java.util.ArrayList;
import java.util.Objects;

import nacho.ExternalStorageController;


/**
 * List of Task objects with implementations
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Takes in a string containing task information in custom storage format
     * Returns TaskList object with respective task allocated in the list
     * @param storageInput
     */
    public TaskList(String storageInput) {
        try {
            // Load Incoming Data From storage location
            for (String line : storageInput.lines().toList()) {
                char taskType = line.charAt(0);
                String[] info = line.split(" \\| ");
                Task temp = null;

                if (taskType == 'T') {
                    temp = new TodoTask(info[2]);
                } else if (taskType == 'D') {
                    temp = new DeadlineTask(info[2], info[3]);
                } else if (taskType == 'E') {
                    temp = new EventTask(info[2], info[3], info[4]);
                } else {
                    throw new Exception("Wrong input format");
                }

                if (Objects.equals(info[1], "1")) {
                    temp.markCompleted();
                }
                taskList.add(temp);
            }
        } catch (Exception e) {
            // Highlight wrong input and start fresh
            System.out.println("WRONG INPUT DATA FORMAT!\n Saving wrong content as oldCorrupted.txt and creating new");
            taskList = new ArrayList<>();
            ExternalStorageController.createTempCorruptedFile();
        }

    }

    /**
     * Adds a new Task Object to List
     * @param task Task object to add
     */
    public void addTask(Task task) {
        taskList.add(task);
        ExternalStorageController.updateStore(this.getStorageRepresentation());
    }

    /**
     * Removes specified Task object from list
     * @param taskIndex index of Task being removed
     */
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
        ExternalStorageController.updateStore(this.getStorageRepresentation());
    }

    /**
     * Sorts the Tasklist by Title lexicographically
     * Updates the external database as well
     */
    public void sortTaskByTitle() {
        taskList.sort(new TitleComparator());
        ExternalStorageController.updateStore(this.getStorageRepresentation());
    }

    /**
     * gets speciied Task object
     * @param taskIndex index of target Task
     * @return Task Object at taskIndex
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public int getTotalTasks() {
        return taskList.size();
    }

    /**
     * Converts Task values in current list into custom storage formatted string
     * @return String of formatted information about current tasks
     */
    public String getStorageRepresentation() {
        String itemList = "";
        for (Task task : taskList) {
            itemList = itemList.concat(task.getStorageRepresentation() + "\n");
        }
        return itemList;
    }

    @Override
    public String toString() {
        String itemList = "";
        for (int i = 0; i < taskList.size(); i++) {
            String item = String.format("%d. %s", i + 1, taskList.get(i));
            itemList = itemList.concat(item + "\n");
        }
        return itemList;
    }
}
