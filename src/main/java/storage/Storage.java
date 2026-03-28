package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DawaeException;
import parser.Parser;
import taskstuff.Task;
import taskstuff.TaskList;

public class Storage {
    public final String FILEPATH;
    
    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }
    
    /**
     * Loads tasks from the backing file if it exists.
     * Creates the data directory if necessary.
     *
     * @return a list of tasks reconstructed from the save file; empty if file missing.
     * @throws DawaeException if the file cannot be read or parsed.
     */
    public ArrayList<Task> load() throws IOException, DawaeException {
        ArrayList<Task> taskList = new ArrayList<>();
        assert FILEPATH != null && !FILEPATH.isEmpty() : "FILEPATH should not be null or empty";
        File f = new File(FILEPATH); // just a file obj
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                assert line != null : "Line read from file should not be null";
                Task task = Parser.parseSaved(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            f.createNewFile();
            return taskList;
        }
        assert taskList != null : "Returned taskList should not be null";
        return taskList;
    }
    
    /**
     * Saves the given task list to the backing file in a line-based format.
     * Creates the data directory if necessary.
     *
     * @param tasks the tasks to persist.
     * @throws DawaeException if writing to the file fails.
     */
    public void save(TaskList tasks) throws DawaeException {
        assert tasks != null : "TaskList to save should not be null";
        try (FileWriter fw = new FileWriter(FILEPATH.toString())) {
            for (int i = 0; i < tasks.size(); i++) {
                assert tasks.get(i) != null : "Task to save should not be null";
                fw.write(tasks.get(i).toDataFile() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}