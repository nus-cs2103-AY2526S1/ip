package zell.storage;

import zell.task.Task;
import zell.exception.ZellException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the class used for local storage of tasks for the Zell chatbot
 */
public class Storage {
    /** The file path of where the local storage is stored at */
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all task from the local storage by converting each task in each string format back to the original
     * task.
     * <p>If the /data folder does not exist it will be created</p>
     * <p>If the file does not exist it will also be created</p>
     * @return A list of all the task stored in local storage
     * @throws ZellException if the file does not exist and cannot be created.
     */
    public List<Task> loadTasks() throws ZellException{
        List<Task> tasks = new ArrayList<>();

        File file = new File(this.filePath);

        // Create /data folder if it does not exist
        file.getParentFile().mkdirs();

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                tasks.add(Task.stringToTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException fe) {
            try {
                FileWriter fileWriter = new FileWriter(this.filePath, true);
                fileWriter.close();
            } catch (IOException ie) {
                throw new ZellException("Failed to store task to local storage because of: "
                        + ie.getMessage());
            }
        }

        return tasks;
    }

    /**
     * Stores a task in local storage. This is done by appending the task's string format to the end of
     * the local storage file.
     *
     * @param task The task to be stored in local storage.
     * @throws ZellException if we encounter some issue when storing the file
     */
    public void storeTask(Task task) throws ZellException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(task.taskToString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new ZellException("Failed to store task to local storage because of: " + e.getMessage());
        }
    }

    /**
     * Updates all the tasks in local storage.
     * <p>We have to store every task in the task list since if a task is removed we cannot just remove one
     * line from the local storage file since there will be a gap. We have to shift all the task after the
     * removed one which will incur O(n).
     * </p>
     * <p>So storing all the task when a task is added or removed is still the same
     * time complexity of O(n) but makes it easier to implement.
     * </p>
     * @param tasks The list of tasks to be stored in local storage.
     * @throws ZellException if we encounter some issue when storing the tasks in the file.
     */
    public void updateTasks(List<String> tasks) throws ZellException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (String task: tasks) {
                fileWriter.write(task + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new ZellException("Failed to store task to local storage because of: " + e.getMessage());
        }
    }
}
