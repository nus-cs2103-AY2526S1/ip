package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import misc.TaskBotException;
import parser.Parser;
import task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final File file;
    private Parser parser;

    /**
     * Creates a Storage object and initialises a file with the specified file path
     *
     * @param filePath path of the file where the tasks are stored
     */

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves current list of tasks in local file
     *
     * @param tasks current list of tasks (to be saved)
     * @throws IOException
     */

    public void saveTasks(List<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        assert file != null : "File should not be null";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                bw.write(t.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks stored in the file if it exists
     * Otherwise, a new file is created and an empty task list is returned
     *
     * @return list of tasks stored in file
     */

    public ArrayList<Task> loadTasks() {
        assert file != null : "File should not be null";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String command;
            while ((command = br.readLine()) != null) {
                try {
                    Task t = Parser.parseTask(command);
                    assert t != null : "Parsed task should not be null";
                    tasks.add(t);
                } catch (TaskBotException e) {
                    System.out.println("OOPS!! Line cannot be parsed: " + e.getMessage());
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("OOPS!! File could not be loaded: " + e.getMessage());
        }
        return tasks;
    }
}
