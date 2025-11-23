package burh;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles saving and loading tasks to local storage.
 */
public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private final Path filePath;
    private final Path directoryPath;

    /**
     * Creates a Storage instance with the given file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     * @throws BurhException If the file path is invalid or inaccessible.
     */
    public Storage(String filePath) throws BurhException {
        try {
            this.filePath = Paths.get(filePath).toAbsolutePath().normalize();
            this.directoryPath = this.filePath.getParent();
            
            // Ensure the directory exists and is writable
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
                if (!Files.isWritable(directoryPath)) {
                    throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                        "No write permission for directory: " + directoryPath);
                }
            }
            
            // Ensure the file exists and is writable if it exists
            if (Files.exists(this.filePath) && !Files.isWritable(this.filePath)) {
                throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                    "No write permission for file: " + this.filePath);
            }
            
        } catch (InvalidPathException e) {
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Invalid file path: " + filePath);
        } catch (AccessDeniedException e) {
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Access denied to file: " + filePath);
        } catch (IOException e) {
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Failed to initialize storage: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the save file.
     * Returns an empty task list if the file does not exist.
     *
     * @return A TaskList containing loaded tasks.
     * @throws BurhException If the file cannot be read or the data is corrupted.
     */
    public TaskList load() throws BurhException {
        TaskList tasks = new TaskList();
        
        if (!Files.exists(filePath)) {
            LOGGER.log(Level.INFO, "No save file found at {0}, starting with empty task list", filePath);
            return tasks;
        }

        try {
            List<String> saveData = Files.readAllLines(filePath);
            int lineNumber = 0;
            
            for (String line : saveData) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    LOGGER.log(Level.WARNING, "Empty line {0} in save file, skipping", lineNumber);
                    continue;
                }
                
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length < 3) {
                        LOGGER.log(Level.WARNING, "Invalid data format at line {0}, skipping: {1}", 
                            new Object[]{lineNumber, line});
                        continue;
                    }
                    
                    char type = parts[0].trim().charAt(0);
                    boolean isDone = parts[1].trim().equals("1");
                    Task task = createTaskFromParts(type, parts);
                    
                    if (task != null) {
                        if (isDone) {
                            task.complete();
                        }
                        tasks.addTaskQuiet(task);
                    } else {
                        LOGGER.log(Level.WARNING, "Unknown task type '{0}' at line {1}, skipping", 
                            new Object[]{type, lineNumber});
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Error parsing line " + lineNumber + ": " + e.getMessage(), e);
                    throw new BurhException(BurhException.ErrorType.CORRUPTED_DATA, 
                        "Error in save file at line " + lineNumber + ": " + e.getMessage());
                }
            }
            
            LOGGER.log(Level.INFO, "Successfully loaded {0} tasks from {1}", 
                new Object[]{tasks.size(), filePath});
            return tasks;
            
        } catch (NoSuchFileException e) {
            LOGGER.log(Level.INFO, "No save file found at {0}, starting with empty task list", filePath);
            return tasks;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading from save file: " + filePath, e);
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Failed to read from save file: " + e.getMessage());
        }
    }

    /**
     * Creates a Task object from the given parts array.
     *
     * @param type The task type identifier (T, D, or E).
     * @param parts The array containing the task data.
     * @return A Task object, or null if the type is invalid.
     * @throws BurhException If the data format is invalid.
     */
    private Task createTaskFromParts(char type, String[] parts) throws BurhException {
        try {
            switch (type) {
                case 'T':
                    if (parts.length < 3) {
                        throw new BurhException(BurhException.ErrorType.CORRUPTED_DATA, 
                            "Incomplete TODO data");
                    }
                    return new Todo(parts[2].trim());
                    
                case 'D':
                    if (parts.length < 4) {
                        throw new BurhException(BurhException.ErrorType.CORRUPTED_DATA, 
                            "Incomplete DEADLINE data");
                    }
                    return new Deadline(parts[2].trim(), parts[3].trim());
                    
                case 'E':
                    if (parts.length < 5) {
                        throw new BurhException(BurhException.ErrorType.CORRUPTED_DATA, 
                            "Incomplete EVENT data");
                    }
                    return new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException(BurhException.ErrorType.CORRUPTED_DATA, 
                "Invalid data format: " + e.getMessage());
        }
    }

    /**
     * Saves the current task list to the storage file.
     * Creates a backup of the existing file if it exists.
     *
     * @param tasks The TaskList containing tasks to save.
     * @throws BurhException If the save operation fails.
     */
    public void save(TaskList tasks) throws BurhException {
        if (tasks == null) {
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, "Task list is null");
        }
        
        List<String> taskStrings = tasks.toFileStrings();
        
        try {
            // Create parent directories if they don't exist
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
            }
            
            // Create a backup of the existing file if it exists
            if (Files.exists(filePath)) {
                Path backupPath = filePath.resolveSibling(filePath.getFileName() + ".bak");
                try {
                    Files.copy(filePath, backupPath, 
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Failed to create backup file: " + e.getMessage(), e);
                }
            }
            
            // Write the tasks to the file
            Files.write(filePath, taskStrings, StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
                
            LOGGER.log(Level.INFO, "Successfully saved {0} tasks to {1}", 
                new Object[]{taskStrings.size(), filePath});
                
        } catch (AccessDeniedException e) {
            LOGGER.log(Level.SEVERE, "Access denied when saving to file: " + filePath, e);
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Access denied when saving to file: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving to file: " + filePath, e);
            throw new BurhException(BurhException.ErrorType.FILE_ERROR, 
                "Failed to save tasks to file: " + e.getMessage());
        }
    }
}
