package jobe.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jobe.exception.JobeException;
import jobe.parser.Parser;
import jobe.task.Task;
import jobe.task.TaskList;

/**
 * Storage class for file validation, creation, loading and saving.
 */
public class Storage {
    public static final String TASKS_FILE_PATH_STRING = "./data/jobe.txt";
    public static final Path TASKS_FILE_PATH = Paths.get(TASKS_FILE_PATH_STRING);
    private File file;
    private StringBuilder statusMessages = new StringBuilder();
    
    /**
     * Constructor to load tasks into file if file exists.
     * <p>
     * Else, if parent directory does not exist, one is created.
     * Similarly, if file does not exist one is created.
     *
     * @param taskList Task list containing user's current tasks.
     */
    public Storage(TaskList taskList) {
        this.file = new File(TASKS_FILE_PATH_STRING);
        
        this.createParentDirectory();
        this.createFileIfMissing();
        
        this.loadTasks(taskList);
    }
    
    private void createParentDirectory() {
        File parentDirectory = file.getParentFile();
        
        boolean parentDirectoryExists = parentDirectory == null || parentDirectory.exists();
        if (parentDirectoryExists) {
            return;
        }
        
        if (parentDirectory.mkdirs()) {
            this.statusMessages.append("Parent directory 'data' successfully created!\n");
        } else {
            this.statusMessages.append("Failed to create parent directory 'data'.\n");
        }
    }
    
    private void createFileIfMissing() {
        if (file.exists()) {
            return;
        }
        
        try {
            if (file.createNewFile()) {
                statusMessages.append("File 'jobe.txt' containing tasks successfully created!");
            } else {
                statusMessages.append("File 'jobe.txt' could not be created");
            }
        } catch (IOException ioe) {
            statusMessages.append("Creation of file has failed with error: ")
                    .append(ioe.getMessage())
                    .append('\n');
        }
    }
    
    /**
     * Saves user's task list into a file with the corresponding file path.
     *
     * @param taskList Task list containing user's tasks.
     */
    public void saveTasks(TaskList taskList) {
        try {
            Files.writeString(TASKS_FILE_PATH, taskList.convertToFileFormat());
        } catch (IOException e) {
            statusMessages.append("OOPS!!!! File failed to save.");
        }
    }
    
    /**
     * Loads user's saved tasks from file into task list.
     *
     * @param taskList Task list containing user's tasks.
     */
    public void loadTasks(TaskList taskList) {
        try {
            List<String> lines = Files.readAllLines(TASKS_FILE_PATH);
            for (String line: lines) {
                processTask(line, taskList);
            }
        } catch (IOException e) {
            statusMessages.append("Failed to read from file. Please try again!");
        } catch (JobeException je) {
            statusMessages.append(je.getMessage());
        }
    }
    
    private void processTask(String line, TaskList taskList) throws JobeException {
        Task task = Parser.parseTask(line);
        taskList.addTask(task);
    }
    
    public String getStatusMessages() {
        return this.statusMessages.toString();
    }
}
