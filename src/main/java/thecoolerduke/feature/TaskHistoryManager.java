package thecoolerduke.feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import thecoolerduke.exceptions.InvalidFormatException;
import thecoolerduke.feature.tasks.Deadline;
import thecoolerduke.feature.tasks.Event;
import thecoolerduke.feature.tasks.ToDo;
import thecoolerduke.main.Priority;

/**
 * Acts as storage manager for managing task history of the TaskManager on the disk.
 * Allows for reading and writing only.
 */
public class TaskHistoryManager {
    private final File file;

    /**
     * Initialises the TaskHistoryManager class.
     *
     * @param pathDir TaskHistory file will be saved in this directory.
     */
    public TaskHistoryManager(String pathDir) {
        this.file = new File(pathDir);
    }

    //separates each line of data into comma-separated parameters
    //throws InvalidFormatException if wrong string format or number of parameters
    private String[] parseLine(String line) throws InvalidFormatException {
        //1. Initial check for all lines to have commas
        if (!line.contains(",")) {
            throw new InvalidFormatException("File has incorrect format: \",\" missing");
        }

        String[] taskInfo = line.split(",");

        //2. Check that lines have at least 4 params and isDone is of valid format (0 or 1)
        if (taskInfo.length < 4 || !taskInfo[1].matches("^[01]$")) {
            throw new InvalidFormatException("File has incorrect format: Wrong number of task params");
        }

        return taskInfo;
    }

    private LocalDateTime parseDatetimeInput(String datetimeString) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(datetimeString, Task.DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("File has incorrect format: Wrong datetime format");
        }
    }

    private Priority parsePriorityLevel(String input) throws InvalidFormatException {
        return Priority.fromString(input);
    }

    //separates all additional parameters from type, isDone and name
    //throws InvalidFormatException if incorrect number of additional parameters
    private String[] parseAdditionalParams(String[] taskInfo, int length) throws InvalidFormatException {
        String[] additionalParams = Arrays.copyOfRange(taskInfo, 4, taskInfo.length);

        if (additionalParams.length != length) {
            throw new InvalidFormatException("File has incorrect format: Incorrect additional params");
        }

        return additionalParams;
    }

    private Task getTaskFromLine(String line) throws InvalidFormatException {
        //Check that line is of correct string format
        //split into comma separated sections if valid
        String[] taskInfo = parseLine(line);

        String type = taskInfo[0];
        boolean isDone = taskInfo[1].equals("1");
        String name = taskInfo[2];
        Priority priority = parsePriorityLevel(taskInfo[3]);

        String[] additionalParams;

        //Check that task type is of valid format (T, D, E) and has correct num of additional params
        return switch (type) {
        case "T" -> {
            yield new ToDo(name, isDone, priority);
        }
        case "D" -> {
            additionalParams = parseAdditionalParams(taskInfo, 1);
            LocalDateTime parsedBy = parseDatetimeInput(additionalParams[0]);
            yield new Deadline(name, isDone, priority, parsedBy);
        }
        case "E" -> {
            additionalParams = parseAdditionalParams(taskInfo, 2);
            LocalDateTime parsedFrom = parseDatetimeInput(additionalParams[0]);
            LocalDateTime parsedTo = parseDatetimeInput(additionalParams[1]);
            yield new Event(name, isDone, priority, parsedFrom, parsedTo);
        }
        default -> throw new InvalidFormatException("File has incorrect format: Wrong task type");
        };
    }

    /**
     * Retrieves task history from file specified in pathDir.
     * If file not found or invalid format, creates new file instead.
     *
     * @param taskList The taskList provided by the taskFeature.TaskManager for inserting retrieved history
     * @return true if task history exists, false if task history file not found, invalid, or empty.
     * @throws Error If file unable to be created at pathDir
     */
    public boolean retrieveTaskHistory(ArrayList<Task> taskList) {

        try (Scanner s = new Scanner(file)) {
            taskList.clear();

            while (s.hasNextLine()) {
                Task task = getTaskFromLine(s.nextLine());
                taskList.add(task);
            }

            //flag as no history found (false) if no tasks but file exists
            //else flag as true
            return !taskList.isEmpty();

        } catch (FileNotFoundException e) {
            try {
                //create directories for history file if not exist

                Files.createDirectories(Path.of(file.getPath()).getParent());

                //create new history file
                boolean createFileResult = file.createNewFile(); //result added to suppress return

                assert createFileResult : "A new history file should be created.";

                return false;

            } catch (IOException ioe) {
                throw new Error(ioe.getMessage());
            }
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
            try {
                //delete file if is invalid format
                if (file.exists()) {
                    boolean deleteFileResult = file.delete(); //result added to suppress return

                    assert deleteFileResult : "Invalid history file should be deleted.";
                }



                //create new history file
                boolean createFileResult = file.createNewFile(); //result added to suppress return

                assert createFileResult : "A new history file should be created.";

                return false;

            } catch (IOException ioe) {
                throw new Error(ioe.getMessage());
            }

        }
    }

    /**
     * Updates task history in file specified in pathDir
     *
     * @param history The taskList provided by the taskFeature.TaskManager for updating history
     * @throws Error If unable to write to file
     */
    public void updateHistory(ArrayList<Task> history) {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task: history) {
                fw.write(task.saveTask() + "\n");
            }

        } catch (IOException e) {

            throw new Error("Unable to write to File");
        }
    }
}
