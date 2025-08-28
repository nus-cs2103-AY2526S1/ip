package beebong.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beebong.exception.BBongException;
import beebong.exception.InvalidSerializedTaskDataException;
import beebong.exception.MissingSaveFileException;
import beebong.task.Task;

/**
 * Handles the reading and writing of task data to a persistent storage file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath the path of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Referenced from: https://www.w3schools.com/java/java_files_create.asp
    // and https://www.w3schools.com/java/java_files_read.asp
    /**
     * Returns a list of tasks read from the save file.
     * <p>
     * The file is read line by line, and each line is deserialized into a
     * {@link Task} instance. If the file does not exist or contains corrupted
     * data, an exception is thrown.
     * </p>
     *
     * @return List of tasks loaded from the file.
     * @throws MissingSaveFileException If save file does not exist.
     * @throws InvalidSerializedTaskDataException If save file data is in an invalid format.
     */
    public List<Task> readTasksFromFile() throws MissingSaveFileException, InvalidSerializedTaskDataException {
        //Check if File Exists
        File saveFile = new File(this.filePath);
        // If File does not exist, do nothing
        if (!saveFile.exists()) {
            throw new MissingSaveFileException();
        }
        //Else Read the saved Tasks from the file
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(saveFile);
            while (reader.hasNextLine()) {
                String taskStr = reader.nextLine();
                tasks.add(Task.deserializeTask(taskStr));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new MissingSaveFileException();
        } catch (InvalidSerializedTaskDataException e) {
            throw new InvalidSerializedTaskDataException("Unable to read all saved task data, data is corrupted!");
        }
        return tasks;
    }

    /**
     * Writes the given list of tasks to the save file in serialized form.
     * <p>
     * If the list of tasks is empty, no data is written to the file. Existing
     * data is overwritten each time this method is called.
     * </p>
     *
     * @param tasks List of tasks to write to the file.
     * @throws BBongException If an error occurs while writing to the file.
     */
    public void writeTasksToFile(List<Task> tasks) throws BBongException {
        // No need to check if the File exists before writing
        // as FileWriter automatically handles that for us.

        // Check if tasks is empty
        if (tasks.isEmpty()) {
            return;
        }

        // Write Task List to File
        try {
            // No need to append, just write new as
            // we always read existing data and append within the taskList
            FileWriter writer = new FileWriter(this.filePath);
            for (Task t : tasks) {
                writer.write(t.serializeTask() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new BBongException("Unable to save tasks to file.");
        }
    }
}
