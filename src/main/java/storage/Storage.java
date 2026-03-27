package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import parser.Parser;
import task.Task;
import util.ShrekException;

/**
 * Handles file storage operations for the Shrek application.
 * This class manages loading tasks from and saving tasks to a data file.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the data file for storing tasks
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the data file into memory.
     * If the file or folder does not exist, it creates them and returns an empty task list.
     *
     * @return ArrayList&lt;Task&gt; the list of tasks loaded from file
     */
    public ArrayList<Task> load() {
        // open file, read line by line, parse each into Task objects
        // return as ArrayList<Task>
        assert file != null : "File should not be null";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks; // empty task list
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Task task = Parser.parseTaskFromFile(line);
                    assert task != null : "Parsed task should never be null";
                    tasks.add(task);
                } catch (ShrekException e) {
                    System.out.println("Skipping corrupted line: " + e.getMessage());
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves all current tasks into the data file.
     * Overwrites the file with the latest state of the task list.
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        assert file != null : "File should not be null";

        // open file, write tasks line by line in a chosen format
        // e.g., "T | 1 | read book"
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                bw.write(t.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
