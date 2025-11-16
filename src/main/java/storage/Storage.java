package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import command.Parser;
import exception.CorruptedFileException;
import exception.FrennyException;
import exception.InvalidCommandException;
import exception.TimeFormatException;
import task.Task;
import task.TaskList;

/**
 * The Storage class is responsible for reading from and writing to the task history file.
 * It handles file initialization, reading tasks into the TaskList, and writing tasks from the TaskList to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the history file.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }

    private void reinitFile() throws IOException {
        File historyFile = new File(filePath);
        if (historyFile.exists()) {
            historyFile.delete();
        }
        historyFile.getParentFile().mkdirs();
        historyFile.createNewFile();
    }

    /**
     * Handles the scenario when the history file is corrupted.
     * Prompts the user to decide whether to attempt recovery or reinitialize the file.
     */
    private void handleCorruptedFile() {
        try {
            reinitFile();
        } catch (IOException e) {
            System.out.println("An error occurred while reinitializing the file.");
        }
        System.out.println("The history file was corrupted and has been reinitialized.");
    }

    /**
     * Reads tasks from the history file and populates the provided TaskList.
     * If the file does not exist, it creates a new one.
     * If the file is corrupted, it handles the corruption appropriately.
     *
     * @param taskList The TaskList to populate with tasks from the file.
     */
    public void readFile(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        try {
            File historyFile = new File(filePath);
            if (historyFile.exists()) {
                try (Scanner fileScanner = new Scanner(historyFile)) {
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        Parser.processHistory(taskList, line);
                    }
                } catch (InvalidCommandException | TimeFormatException | FrennyException | CorruptedFileException e) {
                    handleCorruptedFile();
                }
            } else {
                historyFile.getParentFile().mkdirs();
                historyFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            handleCorruptedFile();
        }
    }

    /**
     * Writes the tasks from the provided TaskList to the history file.
     *
     * @param taskList The TaskList containing tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeFile(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        List<Task> items = taskList.getList();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task item : items) {
                // Write each task to the file
                fw.write(item.generateHistoryFileEntry() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
        }
    }
}
