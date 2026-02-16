package mambo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mambo.parser.Parser;
import mambo.task.Task;
import mambo.task.TaskList;

/**
 * Represents a system used to handle all operations involving saving a task list locally.
 * Able to handle operations to initialize the task list save file, load the task list
 * saved in the file and write to the save file when the chatbot closes.
 *
 * @author kentalim2
 */
public class TaskListFileManager {
    private static final Path DATA_DIRECTORY = Paths.get("data");
    private static final Path FILE_PATH = DATA_DIRECTORY.resolve("tasklist.txt");

    /**
     * Initializes the data/tasklist.txt data file if it does not already exist
     */
    public void initializeFile() {
        try {
            if (!Files.exists(DATA_DIRECTORY)) {
                Files.createDirectories(DATA_DIRECTORY);
                System.out.println("Created data directory: " + DATA_DIRECTORY);
            }

            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                System.out.println("Created data file: " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
        }
    }

    /**
     * Returns the TaskList that has been saved locally by reading through the file of inputs and
     * adding the specific tasks to the list
     *
     * @return TaskList to be passed into the chatbot as its current TaskList
     */
    public TaskList loadFile() {
        TaskList tasks = new TaskList();

        try {
            initializeFile();
            Scanner scanner = new Scanner(FILE_PATH);
            while (scanner.hasNext()) {
                String nextTask = scanner.nextLine();
                tasks.addToList(Parser.parseLineInFile(nextTask));
            }

        } catch (MamboException e) {
            System.out.println("Error loading list: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading list: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given tasklist into the system by writing over the previous
     * save file.
     *
     * @param list TaskList to be saved into the system locally
     */
    public void saveFile(TaskList list) {
        try {
            List<String> tempList = new ArrayList<>();
            for (int i = 1; i < list.listSize() + 1; i++) {
                Task task = list.getTask(i);
                tempList.add(task.convertToFileFormat());
            }
            Files.write(FILE_PATH, tempList);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
