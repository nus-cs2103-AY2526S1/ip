package yuan.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import yuan.task.Task;
import yuan.tasklist.TaskList;

/**
 * Handles saving and loading of tasks from a local file.
 *
 * <p>AI-Assisted: JavaDoc suggested by ChatGPT and refined manually.</p>
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath the path of the file used for saving/loading tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from storage file
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                taskList.addTask(Task.fromStorageFormat(line));
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves tasks in a storage file
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList.getTasks()) {
                bw.write(task.toStorageFormat());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

}
