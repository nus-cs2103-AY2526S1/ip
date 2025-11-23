package Mithrandir.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import Mithrandir.TaskList;
import Mithrandir.task.Task;
import Mithrandir.task.Tasks;
import Mithrandir.util.FileParser;


public class FileStorage {
    private final Path saveFilePath;
    private final Path archiveFilePath;

    public FileStorage(Path saveFilePath, Path archiveFilePath) {
        this.saveFilePath = saveFilePath;
        this.archiveFilePath = archiveFilePath;
    }

    public FileStorage(String saveFilePath, String archiveFilePath) {
        this.saveFilePath = Path.of(saveFilePath);
        this.archiveFilePath = Path.of(archiveFilePath);
    }

    /**
     * Saves the given task data to a file specified by the file path.
     * Creates the parent directories and the file if they do not already exist.
     *
     * @param tasks The string representation of tasks to be stored in the file.
     * @throws IOException If an I/O error occurs while creating directories, file, or writing to the file.
     */
    public void store(String tasks, boolean isArchive) throws IOException {
        try {
            Files.createDirectories(isArchive ? this.archiveFilePath.getParent() : this.saveFilePath.getParent());
            if (!Files.exists(isArchive ? this.archiveFilePath : this.saveFilePath)) {
                Files.createFile(isArchive ? this.archiveFilePath : this.saveFilePath);
            }
            FileWriter fw = new FileWriter(isArchive ? this.archiveFilePath.toString() : this.saveFilePath.toString()
                    , isArchive);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            throw new IOException("Failed to store tasks to file: " + e.getMessage());
        }
    }


    /**
     * Loads the task list from the file specified by the file path.
     * If the file does not exist, an empty task list is returned.
     *
     * @return The task list loaded from the file.
     * @throws Exception If an error occurs while reading the file or parsing the task data.
     */
    public TaskList loadTaskList() throws Exception {
        File file = new File(this.saveFilePath.toString());
        if (!file.exists()) {
            return new TaskList();
        }
        TaskList taskList = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                HashMap<String, String> content = FileParser.parseFileContent(line);
                String taskType = content.get("Type");
                Task task = Tasks.valueOf(taskType).createTask(content.get("CompletionStatus"), content.get(
                        "Description"));
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new IOException("Failed to read tasks from file: " + e.getMessage());
        }
    }
}
