package scribbles.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scribbles.exception.ScribblesException;
import scribbles.parser.Parser;
import scribbles.task.DeadlineTask;
import scribbles.task.EventTask;
import scribbles.task.Task;
import scribbles.task.ToDoTask;
import scribbles.tasklist.TaskList;

/**
 * Provides a handle to manage storage for data in taskList.
 */
public class Storage {
    private final String filename;
    private final String directory;
    private final String filepath;

    /**
     * Constructs default storage path and filename.
     */
    public Storage() {
        this.filename = "ScribblesData.txt";
        this.directory = "./data/";
        this.filepath = directory + filename;
    }

    /**
     * Constructs specified storage path and filename.
     *
     * @param filename Filename of storage.
     * @param directory Directory to store the file.
     */
    public Storage(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;
        this.filepath = directory + filename;
    }

    /**
     * Loads taskList data from storage file.
     *
     * @return A list of task data from file.
     * @throws ScribblesException If file not found or encountering error when loading.
     */
    public ArrayList<Task> loadFile() throws ScribblesException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File directory = new File(this.directory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(this.filepath);
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = decodeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            throw new ScribblesException("scribbles.Scribbles data not found: " + e.getMessage());
        } catch (IOException e) {
            throw new ScribblesException("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves task data from taskList into storage file.
     *
     * @param taskList taskList that contains the task data.
     */
    public void saveFile(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        try {
            FileWriter fw = new FileWriter(this.filepath);
            for (Task task : tasks) {
                fw.write(task.encode() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Helper method to decode task data when loading file
    private Task decodeTask(String taskData) {
        String[] tokens = taskData.split(" \\| ");
        try {
            String taskType = tokens[0];
            boolean isDone = tokens[1].equals("1");
            String description = tokens[2];

            switch (taskType) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                LocalDateTime by = Parser.parseDateTime(tokens[3]);
                return new DeadlineTask(description, by, isDone);
            case "E":
                LocalDateTime from = Parser.parseDateTime(tokens[3]);
                LocalDateTime to = Parser.parseDateTime(tokens[4]);
                return new EventTask(description, from, to, isDone);
            default:
                throw new ScribblesException("Invalid task type");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("A task data is found to be corrupted from save: " + e.getMessage());
            System.out.println("Skipped loading that task");
        } catch (Exception e) {
            System.out.println("Unknown data found: " + e.getMessage());
        }

        return null;
    }
}
