package tony.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tony.parsers.DateTimeManager;
import tony.tasks.Deadline;
import tony.tasks.Event;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.tasks.ToDo;

/**
 * Handles saving and loading of tasks to and from a local file.
 * The {@code Storage} class ensures persistence of task data by
 * writing a {@link TaskList} to a save file and reloading it when the program starts.
 */
public class Storage {

    private final File saveFile;

    /**
     * Constructs a new {@link Storage} by parsing the filepath of the text file.
     * Creates a new text file only if it does not already exist.
     *
     * @param saveFilePath The relative file path of the text file to store the list of tasks.
     */
    public Storage(String saveFilePath) {
        this.saveFile = new File(saveFilePath);
        createIfNotExists();
    }

    /**
     * Creates the save file and its parent directories if they do not already exist.
     * Prints an error message if the file cannot be created.
     */
    private void createIfNotExists() {
        try {
            File parentDir = saveFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating save file: " + e.getMessage());
        }
    }

    /**
     * Saves all tasks in the given {@link TaskList} to the save file.
     * Each task is written in its data format representation.
     *
     * @param list the {@code TaskList} to save
     */
    public void save(TaskList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            for (Task task : list.getList()) {
                writer.write(task.toDataFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to save file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the save file into the given {@link TaskList}.
     * The file is read line by line and each line is parsed into a
     * {@link ToDo}, {@link Deadline}, or {@link Event} task based on its type.
     * Marks the task as done if specified in the file.
     *
     */
    public TaskList load() {
        TaskList list = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], DateTimeManager.parse(parts[3]));
                    break;
                case "E":
                    String[] duration = parts[3].split(" - ", 2); // split only once
                    String fromStr = duration[0].replace("from ", "").trim();
                    String toStr = duration[1].replace("to ", "").trim();
                    task = new Event(parts[2], DateTimeManager.parse(fromStr), DateTimeManager.parse(toStr));
                    break;
                default:
                    throw new IOException("Error: Cannot load file.");
                }
                if (isDone) {
                    task.markDone();
                }
                list.addTask(task);
            }
            return list;
        } catch (IOException e) {
            System.out.println("Error loading save file: " + e.getMessage());
        }
        return list;
    }
}
