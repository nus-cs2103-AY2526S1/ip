package marquess;

import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;
import marquess.task.Deadline;
import marquess.task.Event;
import marquess.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of Marquess data from txt storage.
 */
public class Storage {
    public static final int NUM_PARAMETERS_INITIAL = 2;
    public static final int NUM_PARAMETERS_TODO = 3;
    public static final int NUM_PARAMETERS_DEADLINE = 4;
    public static final int NUM_PARAMETERS_EVENT = 5;

    public static final String TASK_MARKED_INDICATOR = "1";

    private final File file;

    /**
     * Constructor for Storage class using a file path.
     *
     * @param filePath File path of the txt file to be used.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean success = file.getParentFile().mkdirs();
                success = success && file.createNewFile();
                assert success;
            } catch (IOException e) {
                System.err.println("Cannot create storage file: " + e);
            }
        }
    }

    /**
     * Saves a task list to file.
     *
     * @param taskList Task list to be saved.
     */
    public void save(TaskList taskList) {
        String formatted = taskList.exportTasks();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(formatted);
            fw.close();
        } catch (IOException e) {
            System.err.println("Cannot write tasks to file: " + e);
        }
    }

    /**
     * Loads tasks to a task list.
     *
     * @param taskList Task list to be loaded to.
     */
    public void load(TaskList taskList) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                try {
                    loadLine(taskList, nextLine);
                } catch (MarquessException e) {
                    System.err.println("Task cannot be loaded: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find task storage file: " + e);
        }
    }

    /**
     * Loads a line from txt storage to the task list.
     *
     * @param taskList Task list to be loaded to.
     * @param line Line to be processed
     */
    public void loadLine(TaskList taskList, String line) throws MarquessException {
        String[] taskSplit = line.split(",", NUM_PARAMETERS_INITIAL);
        switch (taskSplit[0]) {
        case "T":
            taskSplit = line.split(",", NUM_PARAMETERS_TODO);
            taskList.addTask(new Todo(taskSplit[1].equals(TASK_MARKED_INDICATOR),
                    taskSplit[2]));
            break;
        case "D":
            taskSplit = line.split(",", NUM_PARAMETERS_DEADLINE);
            taskList.addTask(new Deadline(taskSplit[2].equals(TASK_MARKED_INDICATOR),
                    taskSplit[3], taskSplit[1]));
            break;
        case "E":
            taskSplit = line.split(",", NUM_PARAMETERS_EVENT);
            taskList.addTask(new Event(taskSplit[3].equals(TASK_MARKED_INDICATOR),
                    taskSplit[4], taskSplit[1], taskSplit[2]));
            break;
        default:
            throw new InvalidParameterException("Invalid Task");
        }
    }
}
