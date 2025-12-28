package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.Task;

/**
 * The FileManager class handles saving and loading tasks to/from a JSON file.
 * Uses Jackson library for JSON serialization and deserialization.
 * The data is stored in a file located at
 * {@value #DATA_DIR}/{@value #DATA_FILE}.
 */
public class FileManager {
    /**
     * The directory where data files are stored.
     * Default value: "./data"
     */
    private static final String DATA_DIR = "./data";
    private static final String DATA_FILE = DATA_DIR + "/yapper_data.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.findAndRegisterModules();
    }

    /**
     * Saves a list of tasks to a JSON file.
     * Creates the data directory if it doesn't exist.
     *
     * @param tasks the list of tasks to be saved. Cannot be null.
     * @throws IOException if an I/O error occurs during file operations
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            Path dirPath = Paths.get(DATA_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            assert Files.exists(dirPath) : "File directory should exist";

            String output = objectMapper.writerFor(objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Task.class)).writeValueAsString(tasks);

            FileWriter fw = new FileWriter(DATA_FILE);
            fw.write(output);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a JSON file into an ArrayList.
     *
     * If the data file doesn't exist, returns an empty ArrayList.
     * If the file exists but is corrupted or cannot be read, returns an empty
     * ArrayList
     * and prints an error message.
     *
     * @return an ArrayList containing the loaded tasks. Returns an empty list
     *         if the file doesn't exist or cannot be read.
     */
    public static ArrayList<Task> loadTasks() {
        System.out.println("Loaded Tasks");
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class));
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
