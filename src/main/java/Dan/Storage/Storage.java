package Dan.Storage;

import Dan.Task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import Dan.Parser.Parser;

public class Storage {
    private Path filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath the Path object representing the location of the storage file
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }


    /**
     * Creates the necessary directories and storage file if they do not exist.
     * Creates parent directories if needed, then creates the storage file.
     *
     * @return true if directories and file are successfully created or already exist, false if an error occurs
     */
    public boolean createDirectoriesAndFile() {
        try {
            Path parentDir = this.filePath.getParent();

            // Create parent directories if needed
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Initializes the storage by creating necessary directories and files.
     * This method ensures the storage file and its parent directories exist before use.
     *
     * @return this Storage object if initialization is successful, null if initialization fails
     */
    public Storage load() {
        if (createDirectoriesAndFile()) {
            return this;
         } else {
            return null;
        }
    }

    /**
     * Reads and parses the storage file to retrieve all tasks.
     * Returns an empty list if the file cannot be read or parsed.
     *
     * @return an ArrayList of Task objects loaded from the storage file
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);
            ArrayList<String> linesArr = new ArrayList<>(lines);
            tasks = Parser.parseDataStringsToTasks(linesArr);
            return tasks;
        } catch (IOException e) {
            return tasks;
        }

    }

    /**
     * Writes the provided list of tasks to the storage file.
     * Converts tasks to their string representation and saves them to the file.
     * Prints a confirmation message upon successful write or an error message if writing fails.
     *
     * @param tasks the ArrayList of Task objects to be written to storage
     */
    public void writeData(ArrayList<Task> tasks) {
        try {
            File dataFile = filePath.toFile();
            FileWriter fw = new FileWriter(dataFile);

            String dataString = Parser.parseTasksToDataString(tasks);

            fw.write(dataString);
            fw.close();
            System.out.println("Wrote to file: " + dataString);
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }
}
