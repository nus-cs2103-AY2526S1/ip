package talkgpt.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import talkgpt.TalkgptException;
import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;

/**
 * Handles reading from and writing to the storage file for tasks in the TalkGPT application.
 * Provides methods to load, save, delete, and update tasks in persistent storage.
 */
public class Storage {
    private String path;
    private File file;
    private TaskList list = new TaskList();

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path The file path for storing and loading tasks.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * Reads and loads the data from the file into the TaskList.
     *
     * @return TaskList containing the contents of the file.
     */
    public TaskList load() {
        File folder = file.getParentFile();

        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            //initialise
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                list.addTask(Task.deserialize(input));
            }

            sc.close();

            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return list;
        }
    }

    /**
     * Writes and saves a serialized task into the file.
     *
     * @param task Task to be saved in the file.
     */
    public void save(Task task) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(task.serialize() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reset() {
        try (FileWriter writer = new FileWriter(path, false)) { // 'false' to overwrite the file

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes and rewrites the tasks in the file after removing the specified task.
     *
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        this.reset();
        for (Task addTask : list) {
            this.save(addTask);
        }
    }

    /**
     * Removes the oldTask from the task list and replaces it with the newTask.
     *
     * @param oldTask oldTask String in the serialized format.
     * @param newTask newTask to be swapped into the list.
     */
    public void update(String oldTask, Task newTask) {
        try {
            Task outdatedTask = Task.deserialize(oldTask);

            int index = list.indexOf(outdatedTask);

            if (index != -1) {
                list.set(index, newTask);
            }

            this.reset();

            for (Task task : list) {
                this.save(task);
            }

        } catch (TalkgptException e) {
            System.out.println(e.getMessage());
        }
    }
}
