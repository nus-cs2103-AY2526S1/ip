package phuc.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import phuc.exception.PhucException;
import phuc.model.DeadlineTask;
import phuc.model.EventTask;
import phuc.model.Task;
import phuc.model.TaskList;
import phuc.model.ToDoTask;
import phuc.util.ErrorHandler;

/**
 * Handles file storage operations for tasks.
 * Provides methods to save tasks to file and load tasks from file.
 */
public class Storage {
    private static final String DEFAULT_FILE_PATH = "./data/Phuc.txt";
    private final String filePath;

    /**
     * Constructs a Storage instance with the default file path.
     */
    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    /**
     * Saves all tasks to the storage file.
     *
     * @param tasks the TaskList containing tasks to save
     * @throws IOException if file writing fails
     */
    public void save(TaskList tasks) throws IOException {
        java.io.File file = new java.io.File(filePath);
        file.getParentFile().mkdirs();
        assert file.createNewFile();

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.writeToFile());
                writer.write("\n");
            }
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return a TaskList containing the loaded tasks
     * @throws IOException if file reading fails
     * @throws PhucException if file format is invalid
     */
    public TaskList load() throws IOException, PhucException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                boolean isDone = parts[1].equals("true");

                switch (parts[0]) {
                case "E":
                    LocalDateTime startDate = ErrorHandler.validateAndParseDateTime(parts[3]);
                    LocalDateTime endDate = ErrorHandler.validateAndParseDateTime(parts[4]);
                    tasks.add(new EventTask(parts[2], startDate, endDate, isDone));
                    break;
                case "D":
                    LocalDateTime deadline = ErrorHandler.validateAndParseDateTime(parts[3]);
                    tasks.add(new DeadlineTask(parts[2], deadline, isDone));
                    break;
                case "T":
                    tasks.add(new ToDoTask(parts[2], isDone));
                    break;
                default:
                    throw new IOException("File format error");
                }
            }
        }

        return tasks;
    }
}
