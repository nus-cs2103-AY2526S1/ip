package jerry.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jerry.exceptions.JerryException;

/**
 * The Storage class is to manage task data, inclusive of handling reading from
 * and writing to file on the file system, managing the storage
 * and retrieval of task-related information in the Jerry application.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task file from the specified file path.
     * If the file does not exist, it creates a new file in the default data directory.
     *
     * @return file object representing the loaded or created file.
     * @throws JerryException if an error occurs during the loading of file.
     */
    public File load() throws JerryException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File("./data");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file = new File(directory, "jerry.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            return file;
        } catch (IOException e) {
            throw new JerryException("Error! Failed to access storage file: " + e.getMessage());
        }
    }

    /**
     * Writes the current string data to the file in the specified file path.
     *
     * @param data string data representing the current state of tasks.
     * @throws JerryException if writing to the file fails.
     */
    public void writeToFile(String data) throws JerryException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new JerryException("Error! Failed to write to storage file: " + e.getMessage());
        }
    }
}
