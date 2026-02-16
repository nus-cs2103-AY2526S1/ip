package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import task.Task;
import task.TaskList;

/**
 * Contains methods to handle manipulation of txt file containing list of tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Sets file location in class instance.
     *
     * @param filePath File location of text file.
     */
    public Storage(String filePath) throws IneffaException {
        this.filePath = filePath;
        ensureFileExists();
    }

    /**
     * Ensures text file exists, else create it.
     */
    private void ensureFileExists() throws IneffaException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new IneffaException("Failed to create data file: " + e.getMessage());
        }
    }

    /**
     * Loads all task into a Task array from txt file.
     *
     * @return Task array.
     * @throws IneffaException If text in file has invalid format.
     * @throws IneffaException If file is not found.
     */
    public ArrayList<Task> loadFileContents() throws IneffaException {
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(filePath);
        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskStr = line.split("\\|");
                if (validateFileText(taskStr)) {
                    throw new IneffaException("Invalid format for task " + line + " in loaded file.");
                }

                CommandsEnum taskType = CommandsEnum.fromShortCode(taskStr[0].trim());
                boolean isDone = Integer.parseInt(taskStr[1].trim()) == 1;
                String description = taskStr[2].trim();
                Task task = Task.parseTask(taskType, isDone, description);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new IneffaException("Error: Please create a ineffa.txt file under [project root]/data/ineffa.txt");
        }
        return tasks;
    }

    /**
     * Iterates though tasks array and writes tasks into file
     *
     * @param tasks array of tasks to add to file.
     */
    public void writeToFile(TaskList tasks) throws IneffaException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks.list()) {
                fw.write(task.getFileString());
            }
            fw.close();
        } catch (IOException e) {
            throw new IneffaException(e.getMessage());
        }
    }

    /**
     * Ensures that text in file adheres to format.
     *
     * @param text Line of text in file
     * @return true if text does not adhere to format.
     */
    private boolean validateFileText(String[] text) {
        return text.length != 3 || text[0].isBlank() || text[1].isBlank() || text[2].isBlank();
    }
}
