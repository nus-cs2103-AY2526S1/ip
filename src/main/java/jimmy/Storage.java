package jimmy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jimmy.exception.JimmyException;
import jimmy.task.Deadline;
import jimmy.task.Event;
import jimmy.task.Task;
import jimmy.task.TaskList;
import jimmy.task.ToDo;

/**
 * Represents a Storage object.
 */
public class Storage {
    private static final int TODO_FIELD_COUNT = 4;
    private static final int DEADLINE_FIELD_COUNT = 5;
    private static final int EVENT_FIELD_COUNT = 6;
    private static final String DELIMITER = "\\|";
    private File storageFile;


    /**
     * Constructs a Storage object.
     *
     * @param filename name for the file of the stored tasks.
     */
    public Storage(String filename) {
        try {
            // Create folder if it does not exist
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdir();
                System.out.println("Created data folder: " + folder.getAbsolutePath());
            }
            this.storageFile = new File(folder, filename);
            if (this.storageFile.createNewFile()) {
                // Create storageFile file if not already created
                System.out.println(String.format("Storage file created: %s at %s", this.storageFile.getName(),
                        this.storageFile.getAbsolutePath()));
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
        assert this.storageFile != null;
    }

    /**
     * Returns an arraylist with all the tasks saved in the hard disk.
     *
     * @return ArrayList of stored tasks.
     */
    public TaskList loadData(TaskList taskList) {
        try {
            // Scanner to read the file
            Scanner s = new Scanner(storageFile);
            // Update the arraylist to store the tasks
            while (s.hasNext()) {
                Task newTask = readData(s.nextLine());
                taskList.addTask(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (JimmyException e) {
            System.out.println(e);
        }
        return taskList;
    }

    /**
     * Returns the appropriate task based on the data parsed.
     *
     * @param dataEntry a line entry in the taskStorage data that represents a task.
     * @return appropriate Task depending on the line entry.
     */
    public Task readData(String dataEntry) throws JimmyException {
        String[] parsedData = dataEntry.split(DELIMITER);
        String taskType = parsedData[1];

        switch (taskType.toLowerCase()) {
        case ("todo"):
            assert parsedData.length == TODO_FIELD_COUNT;
            return createToDo(parsedData);
        case ("deadline"):
            assert parsedData.length == DEADLINE_FIELD_COUNT;
            return createDeadline(parsedData);
        case ("event"):
            assert parsedData.length == EVENT_FIELD_COUNT;
            return createEvent(parsedData);
        default:
            throw new JimmyException("Error in reading data");
        }
    }

    /**
     * Returns a new ToDo task with appropriate information based on the parsed data.
     * @param parsedData Array of strings containing the parsed data
     * @return ToDo task with appropriate information based on the parsed data.
     */
    public ToDo createToDo(String[] parsedData) {
        String tag;
        String description;
        String isCompletedString;

        // Format: TAG|TODO|DESCRIPTION|COMPLETED
        tag = parsedData[0];
        description = parsedData[2];
        isCompletedString = parsedData[3];

        ToDo newTodo = new ToDo(description, isCompletedString.equalsIgnoreCase("true"), tag);
        return newTodo;
    }

    /**
     * Returns a new Deadline task with appropriate information based on the parsed data.
     * @param parsedData Array of strings containing the parsed data
     * @return Deadline task with appropriate information based on the parsed data.
     */
    public Deadline createDeadline(String[] parsedData) throws JimmyException {
        String tag;
        String description;
        String isCompletedString;
        String deadline;

        // Format: TAG|DEADLINE|DESCRIPTION|COMPLETED|DEADLINE
        tag = parsedData[0];
        description = parsedData[2];
        isCompletedString = parsedData[3];
        deadline = parsedData[4];

        Deadline newDeadline = new Deadline(description, isCompletedString.equalsIgnoreCase("true"),
                tag, deadline);

        return newDeadline;
    }

    /**
     * Returns a new Event task with appropriate information based on the parsed data.
     * @param parsedData Array of strings containing the parsed data
     * @return Event task with appropriate information based on the parsed data.
     */
    public Event createEvent(String[] parsedData) throws JimmyException {
        String tag;
        String description;
        String isCompletedString;
        String start;
        String end;

        // Format: TAG|EVENT|DESCRIPTION|COMPLETED|START|END|
        tag = parsedData[0];
        description = parsedData[2];
        isCompletedString = parsedData[3];
        start = parsedData[4];
        end = parsedData[5];

        Event newEvent = new Event(description, isCompletedString.equalsIgnoreCase("true"), tag,
                start, end);
        return newEvent;
    }

    /**
     * Saves the stored tasks in the taskList to the hard disk.
     *
     * @param taskList TaskList of stored tasks.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.storageFile);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i + 1).toStorageString()); // Add 1 to account for zero-indexed position
                fw.write(System.lineSeparator()); // Start next task on a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
