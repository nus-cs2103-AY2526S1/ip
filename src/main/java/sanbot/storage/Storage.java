package sanbot.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sanbot.task.DeadlineTask;
import sanbot.task.EventTask;
import sanbot.task.Task;
import sanbot.task.TodoTask;

/**
 * Utility class for saving and loading tasks to and from file storage.
 */
public class Storage {

    /** Directory name for storing data */
    private static final String DATA_DIR = "data";

    /** File name for storing task data */
    private static final String FILENAME = "taskCache.txt";

    /** Formatter object to parse date-time input */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
            * Gets the file path for task storage, creating directories if they don't exist.
            *
            * @return Path object representing the task cache file
     */
    private static Path getTaskCachePath() {
        try {
            // Get the directory where the JAR is located
            String jarDir = System.getProperty("user.dir");

            // Create path to data directory
            Path dataDir = Paths.get(jarDir, DATA_DIR);

            // Create data directory if it doesn't exist
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
                System.out.println("Created data directory: " + dataDir.toString());
            }

            // Return path to the task cache file
            return dataDir.resolve(FILENAME);

        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
            e.printStackTrace();
            // Fallback to current directory
            return Paths.get(FILENAME);
        }
    }

    /**
     * Saves all tasks to the storage file.
     *
     * @param tasks the list of tasks to save to file.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        Path filePath = getTaskCachePath();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath.toFile()));

            for (Task t : tasks) {
                writer.write(t.toSaveString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads all tasks from the storage file.
     * Parses saved task data and recreates Task objects.
     *
     * @return ArrayList of loaded tasks, empty if file doesn't exist or error occurs.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Path filePath = getTaskCachePath();
        BufferedReader reader = null;

        if (!Files.exists(filePath)) {
            return loadedTasks;
        }

        try {
            reader = new BufferedReader(new FileReader(filePath.toFile()));
            String line = reader.readLine();

            while (line != null) {
                String[] processed = line.split("\\|");
                String type = processed[0].trim();

                if (type.equals("T")) {
                    Task t = new TodoTask(processed[2].trim());

                    if (processed[1].trim().equals("1")) {
                        t.markAsDone();
                    }

                    loadedTasks.add(t);

                } else if (type.equals("D")) {

                    LocalDateTime deadline = LocalDateTime.parse(processed[3].trim(), FORMATTER);
                    Task t = new DeadlineTask(processed[2].trim(), deadline);

                    if (processed[1].trim().equals("1")) {
                        t.markAsDone();
                    }

                    loadedTasks.add(t);

                } else if (type.equals("E")) {
                    LocalDateTime from = LocalDateTime.parse(processed[3].trim(), FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(processed[4].trim(), FORMATTER);

                    Task t = new EventTask(processed[2].trim(), from, to);

                    if (processed[1].trim().equals("1")) {
                        t.markAsDone();
                    }

                    loadedTasks.add(t);

                } else {
                    System.out.println("task could not be read");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedTasks;
    }

    public static ArrayList<Task> getDeadlineTasks() {
        ArrayList<Task> deadlineTasks = new ArrayList<>();
        Path filePath = getTaskCachePath();
        BufferedReader reader = null;

        if (!Files.exists(filePath)) {
            return deadlineTasks;
        }

        try {
            reader = new BufferedReader(new FileReader(filePath.toFile()));
            String line = reader.readLine();

            while (line != null) {
                String[] processed = line.split("\\|");
                String type = processed[0].trim();

                if (type.equals("D")) {

                    LocalDateTime deadline = LocalDateTime.parse(processed[3].trim(), FORMATTER);
                    Task t = new DeadlineTask(processed[2].trim(), deadline);
                    if (processed[1].trim().equals("1")) {
                        t.markAsDone();
                    }

                    deadlineTasks.add(t);

                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deadlineTasks;
    }

}