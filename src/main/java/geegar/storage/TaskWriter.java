package geegar.storage;

import geegar.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Writes the tasks into the given file (Converts the ArrayList<Task>
 * into strings to be saved in the file of type .txt
 */
public class TaskWriter {
    private static final Path TASKS_FILE = Paths.get("data", "tasks.txt");

    /**
     * Takes in the current updated state of the taskList and rewrites the entire file to match the updated list
     *
     * @param taskList the current update state of the taskList
     */
    public static void saveTasks(ArrayList<Task> taskList) {
        try {
            // create directory if doesnt exist
            TASKS_FILE.getParent().toFile().mkdirs();

            FileWriter writer = new FileWriter(TASKS_FILE.toString(), false);
            for (Task task : taskList) {
                writer.write(task.toSaveString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

}
