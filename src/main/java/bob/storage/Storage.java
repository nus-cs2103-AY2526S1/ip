package bob.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;
import bob.task.TaskDecoder;

/**
 * Storage is responsible for saving and loading tasks
 */
public class Storage {
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Constructor for storage
     */
    public Storage() {
    }

    /**
     * load saved tasks from a file into a list
     * 
     * @return ArrayList containing task
     * @throws IOException if there is an error reading file
     */
    public ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> ret = new ArrayList<Task>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return ret;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskDesc = scanner.nextLine();
                Task task = TaskDecoder.retTask(taskDesc);
                ret.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new IOException("Unable to read  file");
        }
        return ret;
    }

    /**
     * Save task to a file
     * 
     * @param tasks The List of task to be saved
     * @throws IOException if there is an error reading file
     */
    public void saveTask(List<Task> tasks) throws IOException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                writer.write(curr.saveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Unable to save task");
        }
    }

}
