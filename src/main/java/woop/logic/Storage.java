package woop.logic;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import woop.ui.Ui;

/**
 * Class that handles save states.
 */
public class Storage {
    private static final Path PATH = Paths.get("data/data.txt");
    private static final Path DIR = Paths.get("data/");

    /**
     * Checks if data directory exists.
     * If not exists, create directory.
     */
    public static void checkDirectory() {
        try {
            if (!Files.exists(DIR)) {
                Files.createDirectory(DIR);
            }
        } catch (IOException e) {
            Ui.showIoError();
        }
    }

    /**
     * Saves tasks from the given TaskList object to data file
     * If I/O error occurs, prints an error message.
     *
     * @param list Current TaskList to store.
     */
    public static void saveTasks(TaskList list) {
        try {
            Files.writeString(PATH, list.getTasksSaveFormat());
        } catch (IOException e) {
            Ui.showIoError();
        }
    }

    /**
     * Retrieves save state from data file.
     * Then, interpret the save state and convert into a TaskList.
     * If it does not exist, return empty TaskList.
     *
     * @return TaskList of tasks in the save state.
     */
    public static TaskList retrieveSave() {
        boolean doesFileExist = !Files.exists(PATH) || !Files.isRegularFile(PATH);
        if (doesFileExist) {
            return new TaskList();
        }

        TaskList tasks = new TaskList();
        try {
            List<String> l = Files.readAllLines(PATH);
            for (String s : l) {
                extractTask(s, tasks);
            }
        } catch (IOException e) {
            Ui.showIoError();
        }

        return tasks;
    }

    private static void extractTask(String line, TaskList tasks) {
        int typeIndex = 0;
        int finishedIndex = 1;
        int nameIndex = 2;
        int tagIndex = 3;

        String[] lineSplit = line.split(" \\| ");

        String taskType = lineSplit[typeIndex];
        boolean isFinished = lineSplit[finishedIndex].equals("1");
        String name = lineSplit[nameIndex];
        String tag = lineSplit[tagIndex];
        if (tag.equals("null")) {
            tag = null;
        }
        switch (taskType) {
        case "D":
            int dateIndex = 4;
            String dueDate = lineSplit[dateIndex];
            tasks.addTask(new DeadlineTask(name, isFinished, tag, dueDate));
            break;
        case "E":
            int startIndex = 4;
            int endIndex = 5;
            String startTime = lineSplit[startIndex];
            String endTime = lineSplit[endIndex];
            tasks.addTask(new EventTask(name, isFinished, tag, startTime, endTime));
            break;
        case "T":
            tasks.addTask(new TodoTask(name, isFinished, tag));
            break;
        default:
            // do nothing
            break;
        }
    }
}
