package ip.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Deadline;
import ip.tasks.Event;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.tasks.ToDo;

/**
 * Class to implement a storage through an external text file
 */
public class FileStorage implements Storage {

    private final File folder;
    private final File data;

    /**
     * Full Constructor
     *
     * @param folderPath Filepath for the folder 'data'
     * @param dataPath   Filepath for the file 'Squiddy.txt'
     */
    public FileStorage(String folderPath, String dataPath) {
        this.folder = new File(folderPath);
        this.data = new File(dataPath);
    }

    /**
     * Alternative constructor with only dataPath param
     *
     * @param dataPath Filepath for the file 'Squiddy.txt'
     */
    public FileStorage(String dataPath) {
        this.data = new File(dataPath);
        this.folder = new File(dataPath.split("/")[0] + "/");
    }

    public File getData() {
        return data;
    }

    public File getFolder() {
        return folder;
    }

    /**
     * Checks if a folder exists
     *
     * @return True if folder exists, false otherwise
     */
    private boolean checkFolderExists() {
        return folder.exists();
    }

    /**
     * Checks if a file exists
     *
     * @return True if file exists, false otherwise
     */
    private boolean checkFileExists() {
        return data.exists();
    }

    /**
     * Creates new folder if it does not exist
     */
    private void createFolder() {
        try {
            boolean isCreated = folder.mkdir();

            assert isCreated : "Unable to create folder";

        } catch (SecurityException e) {
            e.printStackTrace();
            assert false : "Security exception";
        }
    }

    /**
     * Creates new data file if it does not exist
     */
    private void createFile() {
        try {
            boolean isCreated = data.createNewFile();

            assert isCreated : "Unable to create file";
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "IO exception";
        }
    }

    /**
     * Loads the data file into given TaskList
     *
     * @param tasks TaskList to be input into
     * @throws FileNotFoundException  If data file does not exist
     * @throws FileCorruptedException If formatting of data file is wrong
     *                                Used Deepseek to shorten this method
     */
    @Override
    public void loadFile(TaskList tasks) throws FileNotFoundException, FileCorruptedException {
        Scanner s = new Scanner(data);
        int count = 1;

        while (s.hasNext()) {
            try {
                String curr = s.nextLine();
                Task task = createTaskFromLine(curr, count);
                setTaskCompletionStatus(curr, task, count);
                tasks.addTask(task);
                count++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FileCorruptedException(e.getMessage());
            }
        }
    }

    /**
     * Creates a task from String in data file
     *
     * @param line       String in data file
     * @param lineNumber index of task
     * @return Task created
     * @throws FileCorruptedException if Task type is not valid
     */
    private Task createTaskFromLine(String line, int lineNumber) throws FileCorruptedException {
        String[] splitCurr = line.split("/");
        String taskType = splitCurr[0].trim();
        String description = splitCurr[2].trim();

        switch (taskType) {
        case "T":
            return new ToDo(description);
        case "D":
            return new Deadline(description, LocalDate.parse(splitCurr[3].trim()));
        case "E":
            return new Event(description, LocalDate.parse(splitCurr[3].trim()),
                    LocalDate.parse(splitCurr[4].trim()));
        default:
            String err = String.format("Task %d does not have valid type", lineNumber);
            throw new FileCorruptedException(err);
        }
    }

    /**
     * Sets the completion status of task based on String in data file
     *
     * @param line       String in data file
     * @param task       Task created based on String
     * @param lineNumber Index of task
     * @throws FileCorruptedException if completion status is not valid
     */
    private void setTaskCompletionStatus(String line, Task task, int lineNumber) throws FileCorruptedException {
        String[] splitCurr = line.split("/");
        if (splitCurr[1].trim().equals("1")) {
            task.setDone(true);
        } else if (splitCurr[1].trim().equals("0")) {
            task.setDone(false);
        } else {
            String err = String.format("Task %d does not have valid completion status", lineNumber);
            throw new FileCorruptedException(err);
        }
    }

    /**
     * Appends task into data file
     *
     * @param task Task to be stored
     * @throws FileCorruptedException If FileWriter is unable to write into file
     */
    @Override
    public void writeToStorage(Task task) throws FileCorruptedException {
        try {
            FileWriter writer = new FileWriter(data, true);
            String dataString = task.toDataString() + "\n";

            writer.write(dataString);
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }

    /**
     * Rewrites entire data file according to TaskList
     *
     * @param tasks TaskList to be stored
     * @throws FileCorruptedException If FileWriter is unable to write into file
     */
    @Override
    public void rewriteStorage(TaskList tasks) throws FileCorruptedException {
        try {
            FileWriter writer = new FileWriter(data);
            StringBuilder dataString = new StringBuilder();

            for (Task task : tasks) {
                dataString.append(task.toDataString()).append("\n");
            }

            writer.write(dataString.toString());
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }


    /**
     * Checks and then creates File and Folder if they do not exist
     */
    @Override
    public void start() {
        if (checkFolderExists()) {
            System.out.println("Folder loaded");
        } else {
            createFolder();
        }

        if (checkFileExists()) {
            System.out.println("Data loaded");
        } else {
            createFile();
        }
    }
}
