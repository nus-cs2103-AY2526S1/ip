package yappy.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.Task;
import yappy.task.ToDo;



/**
 * Represent the storage manager for Yappy. A <code>Storage</code> object corresponds to
 * a storage with a file path and formatter
 */
public class Storage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final File file;


    /**
     * Constructs a Storage object for managing task storage at the specified file path.
     * <p>
     * This constructor attempts to create the storage file if it does not already exist.
     * If the parent directories of the file path do not exist, it will try to create them.
     * </p>
     *
     * @param filePath The path of the file where tasks are stored.
     * @throws YappyException if the storage file or its parent directories cannot be created or accessed.
     */
    public Storage(String filePath) throws YappyException {
        this.file = Paths.get(filePath).toFile();
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                if (!dataDir.mkdirs()) {
                    throw new YappyException("Unable to create storage directories at: " + dataDir.getPath());
                }
            }
            if (!file.exists() && !file.createNewFile()) {
                throw new YappyException("Unable to create new storage file at: " + filePath);
            }
        } catch (IOException e) {
            throw new YappyException("Unable to create new storage file: " + e.getMessage());
        }
    }

    /**
     * Returns an ArrayList of the tasks that have been read from disk
     *
     * @return Tasks saved to disk from previous interaction
     * @throws YappyException If format is incorrect
     */
    public ArrayList<Task> loadTask() throws YappyException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                tasks.add(lineParse(line));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new YappyException("File not found");
        }
    }

    /**
     * @param tasks The ArrayList containing the task to be saved to disk.
     * @throws YappyException If the method is unable to open the file.
     */
    public void save(ArrayList<Task> tasks) throws YappyException {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);

            for (Task task : tasks) {
                bfw.write(task.toFileString());
            }
            bfw.close();
        } catch (IOException e) {
            throw new YappyException(e.getMessage());
        }
    }
    /**
     * Parses a line from the storage file and returns the corresponding Task object.
     *
     * @param line The line from the storage file to be parsed.
     * @return The Task object represented by the line.
     * @throws YappyException If the format of the line is incorrect.
     */
    public Task lineParse(String line) throws YappyException {
        String[] params = line.split("\\|");
        assert params.length >= 3 : "Incorrect format in storage file!";
        switch (params[0]) {
        case "T":
            return new ToDo(params[2], Boolean.parseBoolean(params[1]));
        case "E":
            return new Event(params[2], Boolean.parseBoolean(params[1]),
                    LocalDateTime.parse(params[3], FORMATTER), LocalDateTime.parse(params[4], FORMATTER));
        case "D":
            return new Deadline(params[2], Boolean.parseBoolean(params[1]),
                    LocalDateTime.parse(params[3], FORMATTER));
        default:
            throw new YappyException("Incorrect format in Yappy.txt file!");
        }
    }
}
