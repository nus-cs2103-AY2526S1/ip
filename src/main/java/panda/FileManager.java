package panda;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import panda.task.Deadline;
import panda.task.Event;
import panda.task.ToDo;

/**
 * FileManager handles the loading and saving of data from the text file,
 * which contains the information of the tasks.
 */
public class FileManager {
    private final String pathName;
    private final Path path;

    /**
     * Constructs FileManager object using the specified path name.
     *
     * @param pathName Path to text file.
     */
    public FileManager(String pathName) {
        this.pathName = pathName;
        path = Paths.get(pathName);
    }

    /**
     * Loads tasks that are find in the text file found at path.
     * <p>
     * If there are issues with the file such that it cannot be loaded properly,
     * IOException is thrown and caught within the method.
     *
     * @param tasks List where tasks are loaded to.
     * @param ui User interface to communicate with user in case of IOException.
     */
    public void loadTasks(TaskList tasks, PandaUi ui) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }
            Scanner fileScanner = new Scanner(path);
            while (fileScanner.hasNextLine()) {
                String[] taskInfo = fileScanner.nextLine().split("\\|");
                switch (taskInfo[1]) {
                case "[X]":
                    break;
                case "[ ]":
                    break;
                default:
                    throw new IOException();
                }
                switch (taskInfo[0]) {
                case "[T]":
                    tasks.addTask(new ToDo(taskInfo[1], taskInfo[2]));
                    break;
                case "[D]":
                    tasks.addTask(new Deadline(taskInfo[1], taskInfo[2], taskInfo[3]));
                    break;
                case "[E]":
                    tasks.addTask(new Event(taskInfo[1], taskInfo[2], taskInfo[3], taskInfo[4]));
                    break;
                default:
                    throw new IOException();
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            ui.showLoadError();
        }
    }

    /**
     * Saves the tasks that are in TaskList.
     * <p>
     * As task deletion and marking/unmarking tasks are not possible
     * to append to text file, the method will overwrite the file each time
     * regardless of the action taken.
     * <p>
     * If the info cannot be saved to the file, IOException is thrown and
     * caught within the method.
     *
     * @param tasks List of tasks to save.
     * @param ui User interface to communicate with user in case of IOException.
     */
    public void saveTasks(TaskList tasks, PandaUi ui) {
        try {
            FileWriter fileWriter = new FileWriter(pathName);
            fileWriter.write(tasks.generateListData());
            fileWriter.close();
        } catch (IOException e) {
            ui.showSaveError();
        }

    }
}
