package apleasebot.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import apleasebot.exceptions.APleaseBotException;
import apleasebot.exceptions.DataException;
import apleasebot.tasks.TaskList;

/**
 * Encapsulates the logic used to check for the storage file, initialises it if needed and write to it
 */
public class Storage {
    private final Path dataPath;

    /**
     * Constructor for the Storage class
     * @param path String file path to the storage file
     */
    public Storage(String path) {
        File dataFile = new File(path);
        File dir = dataFile.getParentFile();

        // Make directory if missing
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new APleaseBotException("Error creating directory: " + dir.getPath());
            }
        }

        // Make file if missing
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new APleaseBotException("Error creating data file: " + e.getMessage());
            }
        }

        this.dataPath = Paths.get(dataFile.getPath());
    }

    /**
     * Method that loads the storage file into a List of its tasks to be used by TaskList
     * @return List of tasks in String form
     */
    public List<String> load() {
        try {
            return Files.readAllLines(dataPath);
        } catch (IOException e) {
            throw new APleaseBotException("Err reading data file: " + e.getMessage());
        }
    }

    /**
     * Method that writes the instance memory of tasks into the storage file
     * @param taskList Instance memory TaskList
     */
    public void close(TaskList taskList) {
        ArrayList<String> entries = new ArrayList<>();
        int len = taskList.getItemCount();
        for (int i = 0; i < len; i++) {
            entries.add(taskList.getTask(i).translateTaskToText());
        }
        try {
            Files.write(dataPath, entries);
        } catch (IOException e) {
            throw new DataException("Err writing file: " + e);
        }
    }
}
