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
import beebong.task.Task;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Referenced from: https://www.w3schools.com/java/java_files_create.asp
    // and https://www.w3schools.com/java/java_files_read.asp
    public List<Task> readTasksFromFile() throws InvalidSerializedTaskDataException {
        //Check if File Exists
        File saveFile = new File(this.filePath);
        // If File does not exist, do nothing
        if (!saveFile.exists()) {
            return null;
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
            throw new InvalidSerializedTaskDataException("Unable to read tasks from file!");
        } catch (InvalidSerializedTaskDataException e) {
            throw new InvalidSerializedTaskDataException("Unable to read all saved task data, data is corrupted!");
        }
        return tasks;
    }

    public void writeTasksToFile(List<Task> tasks) throws BBongException {
        // No need to check if the File exists before writing
        // as FileWriter automatically handles that for us.

        // Write Task List to File
        try {
            // No need to append, just write new as
            // we always read existing data and append within the tasklist
            FileWriter writer = new FileWriter("bbongSave.txt");
            for (Task t : tasks) {
                writer.write(t.serializeTask() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new BBongException("Unable to save tasks to file.");
        }
    }
}
