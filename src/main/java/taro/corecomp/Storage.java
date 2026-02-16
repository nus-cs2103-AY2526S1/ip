package taro.corecomp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import taro.task.Task;

/**
 * The {@code Storage} class is responsible for reading task data from a
 * file and writing task data back to the file. The data is typically
 * stored in a plain text format and used to reconstruct the list of tasks
 * when the program starts.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a {@code Storage} object to handle loading and saving tasks from/to a file.
     *
     * @param filePath the path to the file used for storing task data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, it will be created along with its parent directories,
     * and an empty task list will be returned.
     * Each line in the file is parsed into a {@link taro.task.Task} using
     * {@link Parser#parseTask(String)}.
     *
     * @return an {@link ArrayList} of {@link taro.task.Task} objects loaded from the file;
     *         returns an empty list if the file does not exist or cannot be parsed
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
                return tasks;
            } else {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    Task task = Parser.parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }


    /**
     * Saves the given list of tasks to the storage file.
     * The file will be overwritten if it already exists.
     *
     * @param tasks the {@link TaskList} containing the tasks to save
     */
    public void save(TaskList tasks) {
        assert tasks != null : "Tasks to save should not be null";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
