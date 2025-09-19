package eureka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import eureka.task.Task;

/**
 * Handles reading from and writing to the storage file for tasks.
 * <p>
 * The {@code Storage} class manages persistence by saving tasks to a text file
 * and loading them back into memory when the application starts.
 * Each task is stored in a serialized format and deserialized using
 * {@link Task#parse(String)}.
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath the path to the file where tasks will be saved/loaded
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into memory.
     * <p>
     * If the file does not exist, it will be created (along with
     * the parent directory if necessary). Each non-empty line in the
     * file is parsed into a {@link Task} using {@link Task#parse(String)}.
     * Malformed lines are skipped with an error message.
     *
     * @return an {@link ArrayList} containing the tasks loaded from the file
     */
    public ArrayList<Task> load() {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File parentDir = f.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs(); // create "data/" folder if missing
                }

                if (f.createNewFile()) {
                    System.out.println("Created new history file: " + f.getAbsolutePath());
                } else {
                    System.out.println("Failed to create history file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return taskList;
        }

        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    taskList.add(Task.parse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    /**
     * Updates the storage file with the current task list.
     * <p>
     * Each task is written in its serialized form (via {@link Task#serialise()}),
     * with one task per line. Overwrites the existing file content.
     */
    public void updateFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.serialise() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
}
