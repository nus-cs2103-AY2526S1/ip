package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.TodoTask;


/**
 * Handles reading and writing to a local file.  A <code>Storage</code>
 * object corresponds to a specified  file
 * e.g., <code>"./data/Mark.txt"</code>
 */
public class Storage {
    private String file;

    /**
     * Creates a new Storage object and ensures that
     * both the data directory and the file exist.
     *
     * @param pathString The directory path where the file is
     * @param file       The file path used to read and write tasks
     */
    public Storage(String pathString, String file) {
        try {
            this.file = file;
            Path path = Path.of(pathString);
            Path filePath = Path.of(file);
            if (Files.notExists(path)) {
                Files.createDirectory(path);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
            assert(Files.exists(path));
            assert(Files.exists(filePath));

        } catch (IOException e) {
            System.out.println(e.getMessage() + "ERROR");
        }
    }

    /**
     * Loads tasks from the storage file into an ArrayList.
     * Each line in the file is expected to have the following format:
     * <code>length#isDone#taskType#(optional)dates</code>
     *
     * @return an ArrayList of Task loaded from the file
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lengthPart = line.split("#", 2);
                int length = Integer.parseInt(lengthPart[0]);
                String name = lengthPart[1].substring(0, length);
                line = lengthPart[1].substring(length + 1);
                String[] parts = line.split("#");

                switch (parts[1]) {
                case "T":
                    taskList.add(new TodoTask(name, Boolean.parseBoolean(parts[0])));
                    break;
                case "D":
                    taskList.add(new DeadlineTask(name, Boolean.parseBoolean(parts[0]),
                            LocalDate.parse(parts[2])));
                    break;
                case "E":
                    taskList.add(new EventTask(name, Boolean.parseBoolean(parts[0]),
                            LocalDate.parse(parts[2]), LocalDate.parse(parts[3])));
                    break;
                default:
                    break;
                }

            }
            return taskList;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves all tasks in the given TaskList to the storage file.
     *
     * @param taskList The TaskList to save into the file
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(taskList.save());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
